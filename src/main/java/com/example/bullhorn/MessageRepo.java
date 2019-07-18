package com.example.bullhorn;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long>{

}
