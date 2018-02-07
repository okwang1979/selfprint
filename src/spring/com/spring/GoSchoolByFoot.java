package com.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * ÖÆ¶¯×°ÅäBean
 * @author wangzhqa
 *
 */
@Component("footSchool")
@Qualifier("footSchool1")
//@Primary
@Conditional(EmptySpringCondition.class)
public class GoSchoolByFoot implements IGoSchool {

	public void goSchool() {
		System.out.println("foot");
		
	}
	

}
