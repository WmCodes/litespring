package org.litespring.beans.factory.config;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public interface ConfigurableBeanFactory {

    void  setBeanClassLoader(ClassLoader beanClassLoader);
    ClassLoader getBeanClassLoader();
}
