package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Integer> {
@Query("select max(id)as maxid from MessageModel ")
int maxid();

@Query("select m from MessageModel m where id>:currentId and id<=:maxId ")
List<MessageModel> consoleResult(@Param("currentId") int id,@Param("maxId") int max);
}
