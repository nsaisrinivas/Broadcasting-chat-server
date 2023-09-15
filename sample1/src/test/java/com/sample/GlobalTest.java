package com.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.Gson;
import com.sample.model.MessageModel;
import com.sample.repository.MessageRepository;
import com.sample.services.GlobalService;

@ExtendWith({SpringExtension.class})
@SpringBootTest
 class GlobalTest {

	@Mock
	MessageRepository repository;
	
	@InjectMocks
	GlobalService service;
	
	@Test
	void insertmessage() {
		MessageModel model=new MessageModel("sai","hello","9898989898");
		when(repository.save(model)).thenReturn(model);
		
		MessageModel model1=service.insertMessage(model.getSender(), model.getMessage(), model.getMobilenumber());
		assertThat(model1.getSender()).isSameAs(model.getSender());
	}
	
	@Test
	void mid() {
		when(repository.maxid()).thenReturn(1);
		int mid=service.getMaxid();
		assertThat(mid).isSameAs(1);
	}
	@Test 
	void model() {
		MessageModel model=new MessageModel();
		model.setId(1);
		model.setSender("sai");
		model.setMobilenumber("9898989898");
		model.setMessage("hello");
		assertThat(model.getId()).isSameAs(1);
		assertThat(model.getSender()).isSameAs("sai");
		assertThat(model.getMobilenumber()).isSameAs("9898989898");
		assertThat(model.getMessage()).isSameAs("hello");
	}
	@Test
	void newMessage() {
		MessageModel model=new MessageModel("sai","hello","9898989898");
		List<MessageModel> l=new ArrayList<>();
		l.add(model);
		when(repository.consoleResult(1, 2)).thenReturn(l);
		String s=service.getnewMessage(1, 2);
		Gson json=new Gson();
		assertThat(s).isEqualTo(json.toJson(l));
	}
	
}
