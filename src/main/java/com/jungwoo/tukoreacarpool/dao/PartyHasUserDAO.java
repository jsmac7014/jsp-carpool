package com.jungwoo.tukoreacarpool.dao;

import com.jungwoo.tukoreacarpool.dataobject.PartyHasUserDO;

import java.sql.SQLException;
import java.sql.Timestamp;

public class PartyHasUserDAO extends BaseDAO {
    public void create(PartyHasUserDO partyHasUserDO) {
        connect();
        String sql = "INSERT INTO party_has_user (user_id, party_id, role, created_at) VALUES (?, ?, ?, ?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, partyHasUserDO.getUserId());
            pst.setInt(2, partyHasUserDO.getPartyId());
            pst.setString(3, partyHasUserDO.getRole());
            pst.setTimestamp(4, Timestamp.valueOf(partyHasUserDO.getCreateTime()));

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
