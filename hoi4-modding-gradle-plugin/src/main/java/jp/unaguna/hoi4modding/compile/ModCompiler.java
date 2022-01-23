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
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

public record ModCompiler(Collection<URL> targetClasspath, Path destinationDirectory) {
    public ModCompiler(
            Collection<URL> targetClasspath,
            Path destinationDirectory
    ) {
        this.targetClasspath = targetClasspath;
        this.destinationDirectory = destinationDirectory;

        // validate arguments
        if (targetClasspath == null || targetClasspath.isEmpty()) {
            throw new IllegalArgumentException("'targetClasspath' must contain at least one path.");
        }
        if (destinationDirectory == null) {
            throw new IllegalArgumentException("'destinationDirectory' must not be null.");
        }
    }

    public void compile() throws ModCompileException {
        URLClassLoader targetClassLoader = new URLClassLoader(targetClasspath.toArray(new URL[0]));

        try (ScanResult scanResult = new ClassGraph().enableClassInfo().enableAnnotationInfo().scan();
             ScanResult targetScanResult = new ClassGraph().overrideClassLoaders(targetClassLoader).ignoreParentClassLoaders().enableClassInfo().scan()) {

            ClassInfoList modFileClassList = scanResult.getClassesWithAnnotation(ModFile.class);

            for (ClassInfo classInfo : modFileClassList) {
                System.err.println("A class annotated with ModFile is found: " + classInfo.getName());

                try {
                    // 当該プロジェクト固有のクラス以外を無視する
                    if (targetScanResult.getClassInfo(classInfo.getName()) == null) {
                        System.err.println("Ignore external class " + classInfo.getName());
                        continue;
                    } else {
                        System.err.println("Compile to mod file from " + classInfo.getName());
                    }

                    // インスタンス作成
                    ToFile instance = (ToFile) classInfo.loadClass().getDeclaredConstructor().newInstance();
                    System.err.println("The instance of " + classInfo.getName() + " has been constructed: " + instance);

                    // modファイルへ書き出す
                    for (Pair<String, Hoi4Object> modFile : instance.fileList()) {
                        Path target = destinationDirectory.resolve(modFile.component1());
                        Hoi4Object content = modFile.component2();

                        Files.createDirectories(target.getParent());
                        Files.writeString(target, content.serialize(), StandardOpenOption.CREATE);
                    }
                } catch (Exception e) {
                    throw new ModCompileException(classInfo.getName(), e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ModCompileException {
        Collection<URL> targetClasspath = List.of(Paths.get(args[0]).toUri().toURL());
        Path destinationDirectory = Paths.get(args[1]);

        ModCompiler compiler = new ModCompiler(targetClasspath, destinationDirectory);
        compiler.compile();
    }
}
