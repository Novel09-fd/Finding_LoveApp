package com.novel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novel.entity.DataUser;

public interface DataUserRepository extends JpaRepository<DataUser, Long> {

	DataUser findByUsername(String username);
	
	@Query(value = "SELECT * FROM data_user WHERE usersex = ?1", nativeQuery = true)
	List<DataUser> selectUsersex(String usersex);
	
}
