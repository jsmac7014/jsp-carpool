package com.jungwoo.tukoreacarpool.dataobject;

import java.time.LocalDateTime;

public class PartyHasUserDO {
    private int id;
    private int userId;
    private int partyId;
    private String role;
    private LocalDateTime createTime;

    public PartyHasUserDO(int userId, int partyId, String role, LocalDateTime createTime) {
        this.userId = userId;
        this.partyId = partyId;
        this.role = role;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
