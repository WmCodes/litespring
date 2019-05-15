package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public class BeanCreationException extends BeansException {
    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
