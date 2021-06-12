package com.capgemini.fds.repository;

import com.capgemini.fds.entities.Login;

public interface ILoginRepository {
	
	public Login signIn(Login login);
	public Login signOut(Login login);
}
