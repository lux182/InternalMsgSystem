package com.msg.service.impl;

import static com.msg.utils.StaticMethod.generatePassword;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.msg.controller.event.LoginEvent;
import com.msg.domain.Admin;
import com.msg.repo.AdminRepo;
import com.msg.service.SecurityService;
import com.msg.utils.NormalException;
import com.msg.utils.SessionConstant;
import com.msg.utils.SystemMessage.Hint;
@Service
public class SecurityServiceImpl implements SecurityService {

	@Resource
	AdminRepo adminRepo;
	
	@Override
	public void login(LoginEvent loginEvent) {
		Subject userSubject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				loginEvent.getUsername(), new String(generatePassword(
						loginEvent.getUsername(), loginEvent.getPassword())));
		token.setRememberMe(false);
		try {
			userSubject.login(token);
		} catch (UnknownAccountException uae) {
			throw new NormalException(Hint.USERNAME_HAS_NOT_EXIST);
		} catch (IncorrectCredentialsException ice) {
			throw new NormalException(Hint.PASSWORD_DO_NOT_MATCH);
		} catch (LockedAccountException lae) {
			throw new NormalException(Hint.USERNAME_HAS_NOT_EXIST);
		} catch (AuthenticationException ae) {
			if (ae.getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) ae.getCause();
			}
			throw new NormalException(Hint.UNEXPECTED_EXCEPTION);
		}
	}

	@Override
	public void logout() {
		SecurityUtils.getSubject().logout();
	}

	@Override
	public boolean isAccessAuth() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	@Override
	public Admin getAuthedUser() {
		Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if(null != session){
            	Admin admin = (Admin) session.getAttribute(SessionConstant.USER);
            	if(null!=admin){
            		return admin;
            	}
            }
        }
        
		String username = (String) currentUser.getPrincipal();
		if (!StringUtils.isEmpty(username)) {
			Admin admin = adminRepo.findByUsername(username);
			Session session = currentUser.getSession();
            if(null != session){
            	session.setAttribute(SessionConstant.USER,admin);
            }
			return admin;
		}
		return null;
	}
}
