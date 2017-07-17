package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Damrod on 02.06.2017.
 */
@Entity
public class PlayableCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playableCharacterId;
    private String name;

    private int level;
    private int experience;
    private int ExperienceRequaiered;

    private int maxHp;
    private int currentHp;

    private int strenght;
    private int dexterity;
    private int inteligence;

    private int damage;
    private int defence;
    private int speed;

    private int FireRes;
    private int WaterRes;
    private int NatureRes;

    private boolean isDead;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quest questinProgress;

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    private int timeLeft;


    public Timestamp getQuestionInPregressEndingDate() {
        return questionInPregressEndingDate;
    }

    public void setQuestionInPregressEndingDate(Timestamp questionInPregressEndingDate) {
        this.questionInPregressEndingDate = questionInPregressEndingDate;
    }

    private Timestamp questionInPregressEndingDate;


    public Quest getQuestinProgress() {
        return questinProgress;
    }

    public void setQuestinProgress(Quest questinProgress) {
        this.questinProgress = questinProgress;
    }

    public int getExperienceRequaiered() {
        return ExperienceRequaiered;
    }

    public void setExperienceRequaiered(int experienceRequaiered) {
        ExperienceRequaiered = experienceRequaiered;
    }


    public int getUnusedPoints() {
        return unusedPoints;
    }

    public void setUnusedPoints(int unusedPoints) {
        this.unusedPoints = unusedPoints;
    }

    private int unusedPoints;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getInteligence() {
        return inteligence;
    }

    public void setInteligence(int inteligence) {
        this.inteligence = inteligence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFireRes() {
        return FireRes;
    }

    public void setFireRes(int fireRes) {
        FireRes = fireRes;
    }

    public int getWaterRes() {
        return WaterRes;
    }

    public void setWaterRes(int waterRes) {
        WaterRes = waterRes;
    }

    public int getNatureRes() {
        return NatureRes;
    }

    public void setNatureRes(int natureRes) {
        NatureRes = natureRes;
    }

    public long getPlayableCharacterId() {
        return playableCharacterId;
    }

    public void setPlayableCharacterId(long playableCharacterId) {
        this.playableCharacterId = playableCharacterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
