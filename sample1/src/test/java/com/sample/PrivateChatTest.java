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
import com.sample.model.ChatHistory;
import com.sample.model.RegistrationModel;
import com.sample.repository.ChatRepository;
import com.sample.repository.PrivateChatRepository;
import com.sample.repository.RegistrationRepository;
import com.sample.services.PrivateChatService;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class PrivateChatTest {

	@Mock
	private PrivateChatRepository repository;
	
	@Mock
	private RegistrationRepository repo;
	
	@Mock
	private ChatRepository r;
	@InjectMocks
	private PrivateChatService service;
	
	@Test
	void chathistoryid() {
		when(repository.chathistoryMaxid("9898989898")).thenReturn(1);
		int maxid=service.chathistoryMaxid("9898989898");
		assertThat(maxid).isSameAs(1);
		
	}
	
	@Test
	void chathistory() {
		ChatHistory model1=new ChatHistory("sai",1,"9898989898","8989898989","hi");
		assertThat(model1.getChat_id()).isSameAs(1);
		ChatHistory model =new ChatHistory();
		model.setChat_id(1);
		model.setId(1);
		model.setLastMessage("hello");
		model.setName("sai");
		model.setReceiverPhone("9898989898");
		model.setSenderPhone("8989898989");
		assertThat(model.getId()).isSameAs(1);
		assertThat(model.getChat_id()).isSameAs(1);
		assertThat(model.getLastMessage()).isSameAs("hello");
		assertThat(model.getName()).isSameAs("sai");
		assertThat(model.getReceiverPhone()).isSameAs("9898989898");
		assertThat(model.getSenderPhone()).isSameAs("8989898989");
	}
	
	@Test
	void getHistoryTest() {
		ChatHistory model1=new ChatHistory("sai",1,"9898989898","8989898989","hi");
		List<ChatHistory> l=new ArrayList<>();
		l.add(model1);
		List<String> l2 =new ArrayList<>();
		l2.add("hari");
		when(repository.chatHistory("9898989898")).thenReturn((List<ChatHistory>)l);
		when(repo.finbymobilenumber("9898989898")).thenReturn((List<String>)l2);
		String s=service.getHistory("9898989898");
		Gson json=new Gson();
		assertThat(s).isEqualTo(json.toJson(l));
	}
	
	@Test
	void chatAdd() {
		ChatHistory model1=new ChatHistory("sai",1,"9898989898","8989898989","hi");
		RegistrationModel model=new RegistrationModel("saii","sai@123","8989898989","8989898989.jpg");
		when(repository.searchPerson("9898989898", "8989898989")).thenReturn(model1);
		when(repo.userExistorNot("8989898989")).thenReturn(model);
		int res=service.privateChatAdd("9898989898", "8989898989");
		assertThat(res).isSameAs(1);
		when(repository.searchPerson(null, null)).thenReturn(null);
		when(repo.userExistorNot(null)).thenReturn(null);
		int res1=service.privateChatAdd(null, null);
		assertThat(res1).isSameAs(0);
		when(repository.searchPerson(null, "8989898989")).thenReturn(null);
		when(repo.userExistorNot("8989898989")).thenReturn(model);
		when(r.getChatId(null, "8989898989")).thenReturn(1);
		int res2=service.privateChatAdd(null, "8989898989");
		assertThat(res2).isSameAs(2);
	}
}
