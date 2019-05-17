package org.listspring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v2.PetStoreService;

/**
 * @author wangmeng
 * @date 2019/5/15
 * @desciption
 */
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);
        Assert.assertEquals(petStore.getOwner(),"test");
    }
}
