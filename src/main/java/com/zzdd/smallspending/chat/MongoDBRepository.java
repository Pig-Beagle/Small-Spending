package com.zzdd.smallspending.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBRepository extends MongoRepository<ChatDto, String> {
    Page<ChatDto> findByRoomNo(int roomNo, Pageable pageable);


}
