package org.listspring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTestV2.class,BeanDefinitionTestV2.class,BeanDefinitionValueResolverTest.class
                        ,CustomBooleanEditorTest.class,CustomNumberEditorTest.class,TypeConverterTest.class})
public class V2ALLTests {
}
