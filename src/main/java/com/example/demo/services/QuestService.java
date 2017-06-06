package com.example.demo.services;

import com.example.demo.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Damrod on 06.06.2017.
 */
public interface QuestService extends JpaRepository<Quest, Long> {
    List<Quest> findAll();

    Quest questId(long id);
}