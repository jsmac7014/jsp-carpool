package com.jungwoo.tukoreacarpool.types;

public enum PartyUserType {
    PARTY_OWNER("PARTY_OWNER"),
    PARTY_PASSENGER("PARTY_PASSENGER");

    private final String value;

    PartyUserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
