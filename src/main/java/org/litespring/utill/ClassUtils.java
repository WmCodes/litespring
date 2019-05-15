package org.litespring.utill;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public abstract class ClassUtils {
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
}
