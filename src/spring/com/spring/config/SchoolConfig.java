package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.spring.EmptySpringCondition;
import com.spring.GoSchoolByCar;
import com.spring.IGoSchool;
import com.spring.Student;

@Configuration
@PropertySource("classpath:/properties/app.properties")
public class SchoolConfig {
	@Bean(name="carSchool")
	@Conditional(EmptySpringCondition.class)
	public IGoSchool carSchool(){
		return new GoSchoolByCar();
	}
	@Autowired
	Environment env;
	
	@Bean(name="carStudent")
	@Conditional(EmptySpringCondition.class)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	                               
	public Student student(@Value("#{1}") int sex){
		Student student  =new Student();
		student.setGoSchool(this.carSchool());
		student.setName(env.getRequiredProperty("name"));
//		student.setSex(env.getProperty("sex",Integer.class));
		
		student.setSex(sex);
		return student;
	}

}
