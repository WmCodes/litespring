package org.litespring.beans.factory.config;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public class RuntimeBeanReference {

    private final String beanName;
    public RuntimeBeanReference(String beanName){
        this.beanName = beanName;
    }
    public String getBeanName(){
        return this.beanName;
    }
}
