package jp.unaguna.hoi4modding.compile;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import jp.unaguna.hoi4modding.hoi4file.Hoi4Object;
import jp.unaguna.hoi4modding.struct.ModFile;
import jp.unaguna.hoi4modding.struct.ToFile;
import kotlin.Pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

public class ModCompiler {
    private final Collection<URL> targetClasspath;
    private final Path destinationDirectory;

    public ModCompiler(
            Collection<URL> targetClasspath,
            Path destinationDirectory
    ) {
        this.targetClasspath = targetClasspath;
        this.destinationDirectory = destinationDirectory;

        // TODO: validation
    }

    public void compile() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        URLClassLoader targetClassLoader = new URLClassLoader(targetClasspath.toArray(new URL[0]));

        try(ScanResult scanResult = new ClassGraph().enableClassInfo().enableAnnotationInfo().scan();
            ScanResult targetScanResult = new ClassGraph().overrideClassLoaders(targetClassLoader).ignoreParentClassLoaders().enableClassInfo().scan()) {

            ClassInfoList modFileClassList = scanResult.getClassesWithAnnotation(ModFile.class);

            for (ClassInfo classInfo : modFileClassList) {
                System.err.println("A class annotated with ModFile is found: " + classInfo.getName());

                // 当該プロジェクト固有のクラス以外を無視する
                if(targetScanResult.getClassInfo(classInfo.getName()) == null) {
                    System.err.println("Ignore external class " + classInfo.getName());
                    continue;
                } else {
                    System.err.println("Compile to mod file from " + classInfo.getName());
                }

                // classInfo が ToFile を実装していないクラスであれば例外
                if(!classInfo.implementsInterface(ToFile.class)) {
                    System.err.println("Annotation ModFile is given to class that cannot be compiled to mod: " + classInfo.getName());

                    // TODO: 投げる独自例外を作る
                    throw new RuntimeException("Annotation ModFile is given to class that cannot be compiled to mod: ${classInfo.name}");
                }

                // インスタンス作成
                ToFile instance = (ToFile) classInfo.loadClass().getDeclaredConstructor().newInstance();
                System.err.println("The instance of " + classInfo.getName() + " has been constructed: " + instance);

                // modファイルへ書き出す
                for(Pair<String, Hoi4Object> modFile : instance.fileList()) {
                    Path target = destinationDirectory.resolve(modFile.component1());
                    Hoi4Object content = modFile.component2();

                    try {
                        Files.createDirectories(target.getParent());
                        Files.writeString(target, content.serialize(), StandardOpenOption.CREATE);
                    } catch (Exception e) {
                        // TODO: 投げる独自例外を作る
                        throw new RuntimeException("Failed write to " + target, e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Collection<URL> targetClasspath = List.of(Paths.get(args[0]).toUri().toURL());
        Path destinationDirectory = Paths.get(args[1]);

        ModCompiler compiler = new ModCompiler(targetClasspath, destinationDirectory);
        compiler.compile();
    }
}
