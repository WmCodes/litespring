package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {



    public ClassPathXmlApplicationContext(String configFile){
       super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }


}
