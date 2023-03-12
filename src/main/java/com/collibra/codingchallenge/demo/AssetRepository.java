package com.collibra.codingchallenge.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository
        extends MongoRepository<Asset, String> {
}
