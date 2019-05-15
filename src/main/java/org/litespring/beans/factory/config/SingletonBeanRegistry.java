package org.litespring.beans.factory.config;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public interface SingletonBeanRegistry {

    void regitserSingleton(String beanName,Object singletonObject);
    Object getSingleton(String beanName);
}
