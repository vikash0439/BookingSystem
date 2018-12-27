package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.bean.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findAllById(Long id);
}
