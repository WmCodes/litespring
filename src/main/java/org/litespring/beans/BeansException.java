package org.litespring.beans;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }
}
