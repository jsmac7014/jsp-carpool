package com.jungwoo.tukoreacarpool.dataobject;

import com.jungwoo.tukoreacarpool.types.PartyUserType;

public class PartyUserDO {
    private int id;
    private int partyId;
    private int userId;
    private PartyUserType userType;
    private String createdAt;

    public int getId() {
        return id;
    }

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PartyUserType getUserType() {
        return userType;
    }

    public void setUserType(PartyUserType userType) {
        this.userType = userType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
