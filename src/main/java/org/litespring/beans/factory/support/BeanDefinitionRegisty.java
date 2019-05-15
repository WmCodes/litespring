package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public interface BeanDefinitionRegisty {

    BeanDefinition getBeanDefinition(String beanID);
    void registerBeanDefinition(String beanId,BeanDefinition bd);
}
