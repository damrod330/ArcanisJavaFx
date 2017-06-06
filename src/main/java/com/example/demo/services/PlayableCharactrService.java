package com.example.demo.services;

import com.example.demo.model.PlayableCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Damrod on 04.06.2017.
 */
public interface PlayableCharactrService extends JpaRepository<PlayableCharacter, Long> {
    List<PlayableCharacter> findAll();

    PlayableCharacter findByPlayableCharacterId(long id);
}
