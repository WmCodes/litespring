package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utill.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public class DefaultBeanFactory extends DefaultsSingletonBeanRegistry
            implements ConfigurableBeanFactory,BeanDefinitionRegisty {


    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ClassLoader beanClassLoader;


    public DefaultBeanFactory(){};
    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanId,bd);
    }


    public Object getBean(String beanID) {
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if (bd == null) {
           throw new BeanCreationException("Bean Definition does not exist");
        }

        if (bd.isSingleton()){
            Object bean = this.getSingleton(beanID);
            if (bean == null){
                bean = createBean(bd);
                this.regitserSingleton(beanID,bean);
            }
            return bean;
        }
        return  createBean(bd);

/*        ClassLoader c1 = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = c1.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e){
            throw new BeanCreationException("create bean for"+beanClassName+"failed",e);
        }*/
    }

    private Object createBean(BeanDefinition bd){
        ClassLoader c1 = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = c1.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e){
            throw new BeanCreationException("create bean for"+beanClassName+"failed",e);
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader :ClassUtils.getDefaultClassLoader());
    }
}
