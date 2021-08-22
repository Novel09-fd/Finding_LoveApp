package com.novel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.entity.DataUser;
import com.novel.repository.DataUserRepository;

@Service
public class DataUserService {

	@Autowired
	DataUserRepository dataUserRepository;
	
	public DataUser getUserByUsername(String username) {
		return dataUserRepository.findByUsername(username);
	}
	
	public List<DataUser> getAllUser() {
		return dataUserRepository.findAll();
	}
	
	public String saveUser(DataUser user) {
		dataUserRepository.save(user);
		return "Data Berhasil Terbuat";
	}
	
	public List<DataUser> getUsersex(String usersex) {
		return dataUserRepository.selectUsersex(usersex);
	}
}
