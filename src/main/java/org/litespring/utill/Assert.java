package org.litespring.utill;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public abstract class Assert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
