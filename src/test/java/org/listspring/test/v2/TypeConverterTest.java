package org.listspring.test.v2;


import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMismatchException;


/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
public class TypeConverterTest {

    @Test
    public void testConvertStringToIne(){
        TypeConverter converter = new SimpleTypeConverter();
        try {
            Integer i = converter.convertIfNecessary("3",Integer.class);
            Assert.assertEquals(3,i.intValue());

            converter.convertIfNecessary("3.1",Integer.class);
        }catch (TypeMismatchException e){
            return;

        }
        Assert.fail();
    }


    @Test
    public void testConvetStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();
        try {
            Boolean b = converter.convertIfNecessary("true",Boolean.class);
        Assert.assertEquals(true,b.booleanValue());


            converter.convertIfNecessary("aaaa.1",Boolean.class);
        }catch (TypeMismatchException e){
            return;

        }
        Assert.fail();

    }
}
