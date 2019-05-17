package org.litespring.beans.factory.support;

import org.apache.commons.beanutils.BeanUtils;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utill.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
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

    public Object createBean(BeanDefinition bd){
        //创建实例
        Object bean = instantiateBean(bd);

        //设置属性
        populateBeanUseCommonBeanUtils(bd,bean);
        return bean;
    }

    private Object instantiateBean(BeanDefinition bd){
        ClassLoader c1 = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = c1.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e){
            throw new BeanCreationException("create bean for"+beanClassName+"failed",e);
        }
    }

    //设置属性
    protected void populateBean(BeanDefinition bd,Object bean){
        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs == null || pvs.isEmpty()){
            return;
        }
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            for (PropertyValue pv : pvs){
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd :pds){
                    if (pd.getName().equals(propertyName)){
                        Object convertedValue = converter.convertIfNecessary(resolvedValue,pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean,convertedValue);
                        break;
                    }
                }

            }
        }catch (Exception e){
            throw new BeanCreationException("Failed to botain BeanInfo for class[" + bd.getBeanClassName() + "]", e);
        }
    }

    private void populateBeanUseCommonBeanUtils(BeanDefinition bd,Object bean){
        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs == null || pvs.isEmpty()){
            return;
        }
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);

        try {
            for (PropertyValue pv :pvs){
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();

                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                BeanUtils.setProperty(bean,propertyName,resolvedValue);
            }

        }catch (Exception e){
            throw new BeanCreationException("Populate bean property faild for ["+bd.getBeanClassName()+"]",e);
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
