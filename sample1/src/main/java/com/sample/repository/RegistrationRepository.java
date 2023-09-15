package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.model.RegistrationModel;

public interface RegistrationRepository extends JpaRepository<RegistrationModel, Integer> {

	@Query("select a from RegistrationModel a where a.mobilenumber=:mobilenumber and a.password=:password")
	public RegistrationModel findbymobilenumber(@Param("mobilenumber") String mobilenumber,@Param("password") String password);
	
	@Query("select a from RegistrationModel a where a.mobilenumber=:mobilenumber")
	public RegistrationModel userExistorNot(@Param("mobilenumber") String reciverId);

	@Query(value="select tests.auth.name from tests.auth, tests.chat_history where tests.auth.mobilenumber=tests.chat_history.receiver_phone and sender_phone=:senderId",nativeQuery=true)
	public List<String> finbymobilenumber(@Param("senderId") String senderId);
	
	@Query("select u from RegistrationModel u where u.mobilenumber=:mobilenumber")
	public RegistrationModel findmobile(@Param("mobilenumber") String mobilenumber);
}
