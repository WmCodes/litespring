package org.litespring.utill;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public abstract class ClassUtils {
    private static final Map<Class<?>, Class<?>> wrapperToPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>(8);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>(8);

    static {
        wrapperToPrimitiveTypeMap.put(Boolean.class,boolean.class);
        wrapperToPrimitiveTypeMap.put(Byte.class,byte.class);
        wrapperToPrimitiveTypeMap.put(Character.class,char.class);
        wrapperToPrimitiveTypeMap.put(Double.class,double.class);
        wrapperToPrimitiveTypeMap.put(Float.class,float.class);
        wrapperToPrimitiveTypeMap.put(Integer.class,int.class);
        primitiveTypeToWrapperMap.put(Long.class,long.class);
        wrapperToPrimitiveTypeMap.put(Short.class,short.class);

        for (Map.Entry<Class<?>,Class<?>> entry:wrapperToPrimitiveTypeMap.entrySet()){
            primitiveTypeToWrapperMap.put(entry.getValue(),entry.getKey());
        }
    }
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader c1= null;

        try {
            c1 = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {

        }
        if(c1 == null){
            c1 = ClassUtils.class.getClassLoader();
            if (c1 == null){

                try {
                    c1 = ClassLoader.getSystemClassLoader();
                } catch (Exception e) {

                }
            }
        }
        return c1;

    }

    public static boolean isAssignableValue(Class<?> type,Object value){
        Assert.notNull(type,"Type must bot be null");
        return (value != null ? isAssignable(type,value.getClass()):!type.isPrimitive());
    }

    private static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            Class<?> resolvedPrimitive = wrapperToPrimitiveTypeMap.get(rhsType);
            if (resolvedPrimitive != null && lhsType.equals(resolvedPrimitive)) {
                return true;
            }
        }
        else {
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }
}
