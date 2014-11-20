package com.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.msg.enums.Cfg;
import com.msg.service.MessageService;
import com.msg.service.ViewService;

@Service
public class ViewServiceImpl implements ViewService{
    
	@Resource
	MessageService messageService;
	
    private ModelAndView mv=null;
    
    @Override
    public ViewService model(String viewName) {
        mv=new ModelAndView(viewName);
        return this;
    }
    @Override
    public ModelAndView build() {
        return mv;
    }  

	@Override
	public ViewService put(String key, Object value) {
		mv.addObject(key,value);
		return this;
	}
	@Override
	public ViewService putCfgs() {
		mv.addObject("cfgs",Cfg.values());
		return this;
	}
	@Override
	public ViewService putMessage() {
		mv.addObject("msgs",messageService.getAll(0,20,new Order(Direction.DESC, "pubdate")).getContent());
		return this;
	}

}
