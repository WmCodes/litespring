package org.listspring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomBooleanEditor;

/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
public class CustomBooleanEditorTest {

    @Test
    public void testConvertStringToBoolean(){
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("true");
        Assert.assertEquals(true,((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("false");
        Assert.assertEquals(false,((Boolean)editor.getValue()).booleanValue());

        editor.setAsText("on");
        Assert.assertEquals(true,((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("off");
        Assert.assertEquals(false,((Boolean)editor.getValue()).booleanValue());

        editor.setAsText("yes");
        Assert.assertEquals(true,((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("no");
        Assert.assertEquals(false,((Boolean)editor.getValue()).booleanValue());


        try {
            editor.setAsText("aaaa");
        } catch (IllegalArgumentException e) {
           return;
        }
        Assert.fail();
    }
}
