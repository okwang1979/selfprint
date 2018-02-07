package test.selfprint.start;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.selfprint.pdf.JavaPdfHellowWorldTest;
import test.spring.TestSpringCreatBean;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JavaPdfHellowWorldTest.class ,TestSpringCreatBean.class})
public class SuiteTest {

}
