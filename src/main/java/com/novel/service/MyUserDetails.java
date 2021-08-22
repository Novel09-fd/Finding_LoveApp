package com.novel.service;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novel.entity.DataUser;
import com.novel.repository.DataUserRepository;

@Service
public class MyUserDetails implements UserDetailsService  {

	@Autowired
	DataUserRepository dataUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> usersex = null;
		DataUser user = dataUserRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User tidak ditemukan dengan username : "+username);
		}
		
		usersex = Arrays.asList(new SimpleGrantedAuthority(user.getUsersex()));
		
		return new User(user.getUsername(),user.getPassword(), usersex);
		
	}
}
