package org.litespring.beans;

import java.util.List;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public interface BeanDefinition {

    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";
    public boolean isSingleton();
    public boolean isPrototype();
    String getScope();
    void setScope(String scope);



    public String getBeanClassName();

    public List<PropertyValue> getPropertyValues();



}
