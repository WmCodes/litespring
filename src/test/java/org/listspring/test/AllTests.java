package org.listspring.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.listspring.test.v1.V1AllTests;
import org.listspring.test.v2.V2ALLTests;

/**
 * @author wangmeng
 * @date 2019/5/17
 * @desciption
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({V1AllTests.class,V2ALLTests.class})
public class AllTests {
}
