package org.listspring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTest.class,
                    BeanFactoryTest.class,
                    ResourceTest.class})
public class V1AllTests {
}
