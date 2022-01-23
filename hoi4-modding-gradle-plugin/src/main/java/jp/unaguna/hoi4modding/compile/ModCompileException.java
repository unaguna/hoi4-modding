package jp.unaguna.hoi4modding.compile;

public class ModCompileException extends Exception {
    /**
     * Constructs a new ModCompileException
     *
     * @param className the classname which failed to be compiled into the Mod file.
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ModCompileException(String className, Throwable cause) {
        super(className, cause);
    }
}
