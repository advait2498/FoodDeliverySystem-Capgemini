package com.capgemini.fds.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Login;

@Service
@Transactional
public interface ILoginService {
	
	public Login signIn(Login login);
	public Login signOut(Login login);
}
