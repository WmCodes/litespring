package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegisty;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBU = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    BeanDefinitionRegisty registy;

    public XmlBeanDefinitionReader(BeanDefinitionRegisty registy){
        this.registy = registy;
    }

    public void loadBeanDefinitions(Resource resource){
        InputStream is = null;
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()){
                Element ele = (Element) iter.next();
                String id = ele.attributeValue(ID_ATTRIBU);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
                if (ele.attribute(SCOPE_ATTRIBUTE) != null){
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                this.registy.registerBeanDefinition(id,bd);
            }

        }catch (DocumentException e){
            throw new BeanDefinitionStoreException("IOExcption parsing XML doucument "+resource.getDescription(),e);
        }catch (Exception e) {
            throw new BeanDefinitionStoreException("parse XML document from " + resource.getDescription() + "failed", e);
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
