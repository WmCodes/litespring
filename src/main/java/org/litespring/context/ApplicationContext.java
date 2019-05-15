package org.litespring.context;

import org.litespring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public interface ApplicationContext extends ConfigurableBeanFactory {

    //预留
    Object getBean(String beanID);
}
