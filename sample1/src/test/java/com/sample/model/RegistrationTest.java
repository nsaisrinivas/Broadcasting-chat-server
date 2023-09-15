package com.sample.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith({SpringExtension.class})
@SpringBootTest
class RegistrationTest {

	@Test
	void messages() {
		RegistrationModel model=new RegistrationModel();
		model.setName("sai");
		assertThat(model.getName()).isSameAs("sai");
	}
	
	@Test
	void mobilenumber() {
		RegistrationModel model=new RegistrationModel();
		model.setMobilenumber("9898989898");
		assertThat(model.getMobilenumber()).isSameAs("9898989898");
	}
	
	@Test
	void FileName() {
		RegistrationModel model=new RegistrationModel();
		model.setFilename("9898989898.jpg");
		model.setPassword("saii");
		assertThat(model.getFilename()).isSameAs("9898989898.jpg");
		assertThat(model.getPassword()).isSameAs("saii");
	}
}
