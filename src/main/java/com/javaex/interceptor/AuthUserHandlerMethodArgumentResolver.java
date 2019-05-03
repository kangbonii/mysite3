package com.javaex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaex.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	
	


	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		//쓸수 없는 파라미터
		if(supportsParameter(parameter)==false) {
			//@AuthUser UserVo 이것의 모양이 아니다
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		
		if(session == null) { //세션 없음
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser UserVo ㅇ 
		return session.getAttribute("authUser");
	}
	
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser 안 붙어 있을때
		if(authUser==null) {
			
			return false;
		}
		
		//파라미터 타입이 UserVo 가 아니면 
		if(parameter.getParameterType().equals(UserVo.class)==false) {
			return false;
		}
		
		return true;
	}
	
}
