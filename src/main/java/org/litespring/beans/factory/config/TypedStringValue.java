package org.litespring.beans.factory.config;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public class TypedStringValue {
    private String value;
    public TypedStringValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
