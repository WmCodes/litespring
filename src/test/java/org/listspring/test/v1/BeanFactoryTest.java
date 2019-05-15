package org.listspring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

/**
 * @author wangmeng
 * @date 2019/5/13
 * @desciption
 */
public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    Resource resource = null;

    @Before
    public void setUP() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        resource = new ClassPathResource("petstore-v1.xml");
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");


        Assert.assertTrue(bd.isSingleton());
        Assert.assertFalse(bd.isPrototype());
        Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStore);
        PetStoreService petStore1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertTrue(petStore.equals(petStore1));

    }


    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions(resource);

        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        resource = new ClassPathResource("xxxxxx-v1.xml");
        try {
            reader.loadBeanDefinitions(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

}
