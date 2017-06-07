package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Damrod on 06.06.2017.
 */
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questId;

    private String title;
    private String description;

    private int duration;

    private int reward;
    private String hpLoss;

    private int baseHpCost;
    private int expReward;
    private int questCp;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getHpLoss() {
        return hpLoss;
    }

    public void setHpLoss(String hpLoss) {
        this.hpLoss = hpLoss;
    }

    public long getQuestId() {
        return questId;
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBaseHpCost() {
        return baseHpCost;
    }

    public void setBaseHpCost(int baseHpCost) {
        this.baseHpCost = baseHpCost;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public int getQuestCp() {
        return questCp;
    }

    public void setQuestCp(int questCp) {
        this.questCp = questCp;
    }
}
