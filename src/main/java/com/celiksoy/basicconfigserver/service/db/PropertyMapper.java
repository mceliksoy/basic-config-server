package com.celiksoy.basicconfigserver.service.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper implements RowMapper<KeyValueEntry> {

    @Override
    public KeyValueEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        KeyValueEntry entry = new KeyValueEntry();
        entry.setKey(rs.getString(1));
        entry.setValue(rs.getString(2));
        return entry;
    }

}
