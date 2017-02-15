package com.pylonmusic.playground.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.pylonmusic.playground.domain.AbstractEntity;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity> extends MongoRepository<T, String> {

	int DEFAULT_MAX_RESULTS = 10;
	
	
}
