package org.listspring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception{
        Resource r= new ClassPathResource("petstore-v1.xml");
        InputStream is = null;

        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if (is != null){
                is.close();
            }
        }
    }


    @Test
    public void testFileSystemResource() throws Exception{
        Resource r = new FileSystemResource("D:\\jcwl\\work\\litespring\\src\\test\\resources\\petstore-v1.xml");
        InputStream is = null;

        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if (is != null){
                is.close();
            }
        }

    }

}
