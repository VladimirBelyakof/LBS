package reflection;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Run dynamic created code from process.
 */
public class ProcessCodeBuilder {

    /** Generated class name prefix and splitter. */
    private static final String NAME_PREFIX = "_";

    /** Default generated method modifier 'public'. */
    private static final String DEFAULT_RUN_METHOD_MODIFIERS = "public";

    /** Default general generated method name. */
    private static final String DEFAULT_RUN_METHOD_NAME = "run";


    /**
     * Run code from process code node.
     *
     * @param processID unique process string ID.
     * @param codeNodeID unique ID code node from process.
     * @param innerCode inner code from code node.
     * @param resultType code return type. Warning: simple type and type wrapper is different types.
     * @param <T> need method return type.
     * @return result run method.
     * @throws CannotCompileException if code from node cannot compile.
     * @throws NoSuchMethodException if generated method no found.
     * @throws IllegalAccessException if cannot create class instance or run compiled code.
     * @throws InstantiationException if cannot create class has method with code instance.
     * @throws InvocationTargetException if cannot run compiled code.
     * @throws ClassNotFoundException if exist class no found.
     */
    public static <T> T eval(String processID, long codeNodeID, String innerCode, Class<T> resultType) throws CannotCompileException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        T result = null;
        Class clazz = getOrBuild(buildClassName(processID, codeNodeID), innerCode, resultType);
        Method method = clazz.getDeclaredMethod(DEFAULT_RUN_METHOD_NAME);
        Object instance = clazz.newInstance();
        result = (T) method.invoke(instance);
        return result;
    }

    /**
     * Find or create class. Use exist class if class with this name exist (use old inner code).
     *
     * @param className class name.
     * @param innerCode inner code from code node.
     * @param resultType code return type. Warning: simple type and type wrapper is different types.
     * @param <T> need method return type.
     * @return exist or created class with name generated from process and task ID's has one method with specific code.
     * @throws CannotCompileException if code cannot compile.
     * @throws ClassNotFoundException if exist class no found.
     */
    private static synchronized <T> Class<?> getOrBuild(String className, String innerCode, Class<T> resultType) throws CannotCompileException, ClassNotFoundException {

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClazz = classPool.getOrNull(className);
        Class clazz = null;
        if (ctClazz == null) {
            ctClazz = classPool.makeClass(className);
            CtMethod ctMethod = CtMethod.make(DEFAULT_RUN_METHOD_MODIFIERS + " "  + resultType.getName() + " " + DEFAULT_RUN_METHOD_NAME +
                    "() { " + innerCode + " }", ctClazz);
            ctClazz.addMethod(ctMethod);
            clazz = ctClazz.toClass();
        } else {
            clazz = Class.forName(className);
        }
        return clazz;
    }

    /**
     * Generate new class name from process ID, task ID and constant prefix.
     *
     * @param processID unique string process ID.
     * @param codeNodeID unique code node ID.
     * @return string has process ID, task ID and splitter.
     */
    private static String buildClassName(String processID, long codeNodeID) {
        StringBuilder newClassName = new StringBuilder();
        newClassName.append(NAME_PREFIX);
        newClassName.append(processID);
        newClassName.append(NAME_PREFIX);
        newClassName.append(codeNodeID);
        return newClassName.toString();
    }
}