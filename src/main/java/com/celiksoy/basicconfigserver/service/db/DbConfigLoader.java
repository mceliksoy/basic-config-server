package com.celiksoy.basicconfigserver.service.db;

import com.netas.basicconfigserver.service.AbstractLoader;
import com.netas.basicconfigserver.service.ConfigLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "DbConfigLoader")
@ConditionalOnExpression("'${client.config.type}' == 'DATABASE'")
public class DbConfigLoader extends AbstractLoader implements ConfigLoader {

    @Value("${client.config.db.sql:none}")
    private String clientsConfigDdSql;


    private final JdbcTemplate jdbcTemplate;

    public DbConfigLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void load() {
        Map<String, String> tmp = new HashMap<>();

        if(!StringUtils.isEmpty(clientsConfigDdSql) && !clientsConfigDdSql.equals("none")) {
            System.out.println("----------------------------------------------------------");
            System.out.println("ConfigDB: - Load Conf. from DB : " + clientsConfigDdSql);
            List<KeyValueEntry> confList = jdbcTemplate.query(clientsConfigDdSql, new PropertyMapper());
            if(!CollectionUtils.isEmpty(confList)) {
                confList.stream().forEach(p -> {
                    tmp.put(p.getKey(), p.getValue());
                    System.out.println("ConfigDB: - " + p.getKey() + " = " + p.getValue());
                });
            }
            System.out.println("ConfigDB: - Conf loaded.");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("ERROR : Configuration can not read from DB !! SQL is empty. ");
        }

        propertyMap = tmp;
    }
}
