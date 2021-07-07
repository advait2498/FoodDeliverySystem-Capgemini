package com.cg.fds.service;

import com.cg.fds.entities.User;

public interface ILoginService {
	
	public User signIn(User login);
	public User signOut(User login);
}
