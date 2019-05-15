package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.utill.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public class DefaultsSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

    @Override
    public void regitserSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null ");
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null){
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
        }
        this.singletonObjects.put(beanName,singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
