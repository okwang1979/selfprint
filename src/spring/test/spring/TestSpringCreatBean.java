package test.spring;


import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.IGoSchool;
import com.spring.Student;
import com.spring.config.SpringConfig;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=SpringConfig.class)
@ContextConfiguration(locations={"classpath*:/spring/spring-main.xml"})
public class TestSpringCreatBean {
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	@Autowired() @Qualifier("footSchool")   
	//@Autowired()  //Ê×Ñ¡  
//	  @Resource(name="footSchool")
	private IGoSchool goSchool ;
	
	@Autowired @Qualifier("carStudent")  
	private Student student;
	
	@Autowired
	Environment env;
	
	@Test
	public void testComponent(){
		
		
//		System.out.println("ddd");
		Assert.assertNotNull(goSchool);
		goSchool.goSchool();
		Assert.assertEquals(log.getLog(),"foot\r\n");
		Assert.assertTrue(env.getProperty("name").length()>0);
		Assert.assertEquals(env.getProperty("name"),student.getName());
		
		
		Assert.assertTrue(env.getProperty("sex").length()>0);
		Assert.assertEquals(env.getProperty("sex",Integer.class),student.getSex());
//		log.clear();
//		System.out.println("aaa");
//		Assert.assertEquals(log.getLog(),"aaa\r\n");
		
	}
	

}
