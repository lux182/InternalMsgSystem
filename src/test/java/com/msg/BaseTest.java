package com.msg;

import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.msg.config.SpringMVCConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMVCConfig.class})
@WebAppConfiguration
@Transactional
public abstract class BaseTest {
	
	@Autowired
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	public void printMappingList(){
		Map<String, HandlerMapping> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(wac, HandlerMapping.class, true, false);
		for(String k : map.keySet()){
			HandlerMapping mapping = map.get(k);
			if(mapping instanceof RequestMappingHandlerMapping){
				RequestMappingHandlerMapping rmapping = (RequestMappingHandlerMapping) mapping;
				Map<RequestMappingInfo, HandlerMethod> hm = rmapping.getHandlerMethods();
				for(RequestMappingInfo key : hm.keySet())
				{
					p(key.toString());
				}
			}
		}
		p("OK");
	}
	public void p(String msg){
		System.out.println(msg);
	}
}
