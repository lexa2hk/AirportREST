package com.example.AirportREST.repository;

import com.example.AirportREST.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageEntity, Integer> {
    MessageEntity findByMessage(String message);
}
