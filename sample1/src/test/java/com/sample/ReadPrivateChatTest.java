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

import com.sample.model.Chat;
import com.sample.model.ChatHistory;
import com.sample.model.ChatMessage;
import com.sample.repository.PrivateChatReadRepository;
import com.sample.repository.PrivateChatRepository;
import com.sample.services.ReadPrivateChatService;

@ExtendWith({SpringExtension.class})
@SpringBootTest
 class ReadPrivateChatTest {

	@Mock
	private PrivateChatReadRepository repository;
	
	@Mock
	private PrivateChatRepository repo;
	@InjectMocks
	private ReadPrivateChatService service;
	
	@Test
	void insermessage() {
		ChatMessage message=new ChatMessage("9898989898","hello",1);
		ChatHistory history=new ChatHistory(1,"9898989898","9090909090","");
		
		when(repo.updateLastMessage("hi", 1)).thenReturn(history.setLastMessage("hi"));
		
		when(repository.save(message)).thenReturn(message);
		
		ChatMessage msg=service.insertMessage("9898989898", "hello", 1);
		assertThat(msg.getSenderPhone()).isSameAs(message.getSenderPhone());
		
	}
	@Test
	void readmessage() {
		ChatMessage message=new ChatMessage("9898989898","how",2);
		when(repository.privatechatMaxid(2)).thenReturn(2);
		List<ChatMessage> l=new ArrayList<ChatMessage>();
		l.add(message);
		when(repository.readMessage(2)).thenReturn(l);
		String msg1=service.readMessage(2, 2);
		assertThat(msg1).isSameAs("");
		assertThat(service.readMessage(2, 1)).isEqualTo("[]");
	}
	
	@Test
	void chatmsgmodel() {
		ChatMessage model=new ChatMessage();
		model.setChat_id(2);
		model.setId(1);
		model.setMessage("good");
		model.setSenderPhone("9898989898");
		assertThat(model.getId()).isSameAs(1);
		assertThat(model.getChat_id()).isSameAs(2);
		assertThat(model.getMessage()).isSameAs("good");
		assertThat(model.getSenderPhone()).isSameAs("9898989898");
	}
	
	@Test
	void chat() {
		Chat model1=new Chat("9898989898","8989898989");
		assertThat(model1.getSenderPhone()).isSameAs("9898989898");
		Chat model=new Chat();
		model.setChat_id(1);
		model.setReceiverPhone("8989898989");
		model.setSenderPhone("9898989898");
		assertThat(model.getChat_id()).isSameAs(1);
		assertThat(model.getReceiverPhone()).isSameAs("8989898989");
		assertThat(model.getSenderPhone()).isSameAs("9898989898");
	}
}
