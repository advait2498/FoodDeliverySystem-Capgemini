package com.cg.fds.repository;

import com.cg.fds.entities.User;

public interface ILoginRepository {
	
	public User signIn(User login);
	public User signOut(User login);
}
