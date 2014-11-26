package com.msg.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.msg.enums.Cfg;
import com.msg.utils.HttpClientUtils;
import com.msg.utils.NormalException;
import com.msg.utils.SystemMessage.Hint;

public class RecIdInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = Logger.getLogger(RecIdInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String recId = (String) pathVariables.get("recId");
		if(!StringUtils.isEmpty(recId)){
			StringBuffer url = request.getRequestURL();
			String result=HttpClientUtils.get(Cfg.SESSIONINFO_URL.getValue(),request.getHeader("Cookie"));
			logger.info("url:"+url+" result:"+result);
			if(result!=null){
				if(result.indexOf("\"actorId\":"+recId)<0){
					throw new NormalException(Hint.USER_HAS_BEEN_NOT_LOGIN);
				};
			}
			
		}
		return super.preHandle(request, response, handler);
	}
	
}
