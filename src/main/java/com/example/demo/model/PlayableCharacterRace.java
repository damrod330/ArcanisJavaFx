package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Damrod on 04.06.2017.
 */
@Entity
public class PlayableCharacterRace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playableCharacterRace;

    private String playableCharacterRaceName;
}
