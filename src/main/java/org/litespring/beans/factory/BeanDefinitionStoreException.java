package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
