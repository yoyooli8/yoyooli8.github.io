package com.ai.frame.test.springmvc.resolvers;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MyMethodArgumentsResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AiFormModel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//使用control.xml配置文件生成入参，和校验
		AiFormModel aiFormModel = parameter.getParameterAnnotation(AiFormModel.class);
		String uid = aiFormModel.uid();
		String path = aiFormModel.path();
		
		
		ValidationUtils util;
		ReloadableResourceBundleMessageSource source;
		return null;
	}

}
