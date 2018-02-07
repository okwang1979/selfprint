package com.spring;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EmptySpringCondition implements Condition{

	public boolean matches(ConditionContext paramConditionContext, AnnotatedTypeMetadata paramAnnotatedTypeMetadata) {
		 
		return true;
	}

}
