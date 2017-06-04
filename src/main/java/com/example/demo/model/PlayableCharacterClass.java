package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Damrod on 04.06.2017.
 */
@Entity
public class PlayableCharacterClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playableCharacterClassId;

    private String playableCharacterClassName;

    public long getPlayableCharacterClassId() {
        return playableCharacterClassId;
    }

    public void setPlayableCharacterClassId(long playableCharacterClassId) {
        this.playableCharacterClassId = playableCharacterClassId;
    }

    public String getPlayableCharacterClassName() {
        return playableCharacterClassName;
    }

    public void setPlayableCharacterClassName(String playableCharacterClassName) {
        this.playableCharacterClassName = playableCharacterClassName;
    }
}
