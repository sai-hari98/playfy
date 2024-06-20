package com.saih.playfy.repository;

import com.saih.playfy.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {

    public List<Playlist> findByUserId(String userId);
}
