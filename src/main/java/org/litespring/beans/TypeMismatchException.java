package org.litespring.beans;

/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
public class TypeMismatchException extends Exception {

    private transient Object value;

    private Class<?> requiredType;

    public TypeMismatchException(Object value,Class<?> requiredType){
        super("Fail to convert value:"+value+"to type"+requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue(){
        return value;
    }

    public Class<?> getRequiredType(){
        return requiredType;
    }

}
