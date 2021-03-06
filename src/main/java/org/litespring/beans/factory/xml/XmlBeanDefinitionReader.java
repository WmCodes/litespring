package org.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegisty;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utill.StringUtils;

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

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    BeanDefinitionRegisty registy;

    protected final Log logger = LogFactory.getLog(getClass());

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
                parsePropertyElement(ele,bd);
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

    public void parsePropertyElement(Element beanElem,BeanDefinition bd){
        Iterator iter = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iter.hasNext()){
            Element propElem = (Element) iter.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)){
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(propElem,bd,propertyName);
            PropertyValue pv = new PropertyValue(propertyName,val);
            bd.getPropertyValues().add(pv);
        }
    }

    public Object parsePropertyValue(Element ele,BeanDefinition db,String propertyName){
            String elementName = (propertyName != null)?"<property> element for property '" +propertyName +"'":"<constructor-arg> element";
            boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE)!= null);
            boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE)!= null);
            if (hasRefAttribute){
                String refName = ele.attributeValue(REF_ATTRIBUTE);
                if (!StringUtils.hasText(refName)){
                    logger.error(elementName + "contains empty 'ref' attribute");
                }
                RuntimeBeanReference ref = new RuntimeBeanReference(refName);
                return ref;
            }else if (hasValueAttribute){
                TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
                return valueHolder;
            }
            else {
                throw new RuntimeException(elementName +"must specify a ref or value");
            }
    }

}
