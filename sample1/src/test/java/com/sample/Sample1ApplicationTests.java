package com.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sample.controller.RegisterServlet;
import com.sample.model.RegistrationModel;
import com.sample.repository.RegistrationRepository;
import com.sample.services.RegistrationService;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;


@ExtendWith({SpringExtension.class})
@SpringBootTest
class Sample1ApplicationTests {

	@Mock
	private RegistrationRepository repository;
	
	@Mock
	private RegisterServlet controller;
	@InjectMocks
	private RegistrationService service;
	@Test
	void contextLoads() {
		 RegistrationModel model = new RegistrationModel();
        model.setName("Test Name");
        when(repository.save(ArgumentMatchers.any(RegistrationModel.class))).thenReturn(model);
    
        RegistrationModel created = service.register(model);
        assertThat(created.getName()).isSameAs(model.getName());
        verify(repository).save(model);
	}
	@Test
	void valdate() {
		RegistrationModel model=new RegistrationModel("sai","sai@123","9898989898","9898989898.jpg");
		RegistrationModel model3=new RegistrationModel("sai","sai@1234","9898989898","9898989898.jpg");
		when(repository.findbymobilenumber(model.getMobilenumber(), model.getPassword())).thenReturn(model);
		when(repository.findbymobilenumber(model3.getMobilenumber(), model3.getPassword())).thenReturn(null);
		List<String> model1=service.validate(model);
		List<String> model2=service.validate(model3);
		assertThat(model2).isSameAs(Collections.emptyList());
		assertThat(model1.get(0)).isSameAs(model.getName());
		
	}
	@Test
	void mobilenumber() {
		RegistrationModel model=new RegistrationModel("sai","sai@123","9898989898","9898989898.jpg");
		when(repository.findmobile(model.getMobilenumber())).thenReturn(model);
		when(repository.findmobile(null)).thenReturn(null);
		boolean mobile=service.searchmobile(model.getMobilenumber());
		assertThat(mobile).isSameAs(false);
		boolean mobile2=service.searchmobile(null);
		
		assertThat(mobile2).isSameAs(true);
	}

	@Test
	void file() throws IOException {
		String filename="text.jpg";
		String path="src/main/webapp/static/profileimages/";
		String name="";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",filename,
	              "text/plain", "test data".getBytes());
			name=service.uploadImage(path, mockMultipartFile, "2898989899");
			assertThat(name).isEqualTo("2898989899.jpg");
			Files.deleteIfExists(Paths.get(path+"2898989899.jpg"));
	}
	
}
