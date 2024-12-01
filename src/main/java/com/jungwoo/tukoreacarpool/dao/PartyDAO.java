package com.jungwoo.tukoreacarpool.dao;

import com.jungwoo.tukoreacarpool.dataobject.PartyDO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PartyDAO extends BaseDAO {
    public int createParty(PartyDO party) {
        connect();
        int result = 0;

        String sql = "INSERT INTO party " +
                "(title, description, from_name, to_name, from_address, to_address, size, type, scheduled_at ,created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            pst.setString(1, party.getTitle());
            pst.setString(2, party.getDescription());
            pst.setString(3, party.getFromName());
            pst.setString(4, party.getToName());
            pst.setString(5, party.getFromAddress());
            pst.setString(6, party.getToAddress());
            pst.setInt(7, party.getSize());
            pst.setString(8, party.getType());
            pst.setTimestamp(9, Timestamp.valueOf(party.getScheduledAt()));
            pst.setTimestamp(10, Timestamp.valueOf(party.getCreatedAt()));
            pst.setTimestamp(11, Timestamp.valueOf(party.getUpdatedAt()));

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to create party");
            } else {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
        return result;
    }

    public List<PartyDO> getAllParty() throws SQLException {
        List<PartyDO> partyList = new ArrayList<>();
        PartyDO party;
        String sql = "SELECT * FROM party";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            PartyDO p = new PartyDO();
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setFromName(rs.getString("from_name"));
            p.setToName(rs.getString("to_name"));
            p.setFromAddress(rs.getString("from_address"));
            p.setToAddress(rs.getString("to_address"));
            p.setSize(rs.getInt("size"));
            p.setType(rs.getString("type"));
            p.setScheduledAt(rs.getTimestamp("scheduled_at").toLocalDateTime());
            p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            p.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

            partyList.add(p);
        }
        return partyList;
    }
}
