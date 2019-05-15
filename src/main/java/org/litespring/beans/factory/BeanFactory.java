package org.litespring.beans.factory;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public interface BeanFactory {

    Object getBean(String beanID);
}
