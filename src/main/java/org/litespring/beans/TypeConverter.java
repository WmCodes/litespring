package org.litespring.beans;

/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value,Class<T> requiredTypw) throws TypeMismatchException;
}
