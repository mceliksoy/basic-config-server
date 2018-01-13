package com.celiksoy.basicconfigserver.service.file;

import com.celiksoy.basicconfigserver.service.AbstractLoader;
import com.celiksoy.basicconfigserver.service.ConfigLoader;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "FileConfigLoader")
@ConditionalOnExpression("'${client.config.type}' == 'FILE'")
public class FileConfigLoader extends AbstractLoader implements ConfigLoader {

    // Default Value of this property is none and so the config file will be search in the same directory with jar file.
    @Value("${client.config.file.path:none}")
    private String clientsConfigFilePath;

    // Default Value of this property is config_server_clients.properties
    @Value("${client.config.file.name:config_server_clients.properties}")
    private String clientsConfigFileName;

    @Override
    public void load() {
        File confFile = getConfFile();
        Map<String, String> tmp = new HashMap<>();
        if (confFile.exists() && confFile.isFile() && confFile.canRead()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("ConfigFile: - Load Conf. from file : " + confFile.getAbsolutePath());
            try {
                List<String> lines = FileUtils.readLines(confFile, "utf-8");
                for (String line : lines) {
                    System.out.println("ConfigFile: - " + line);
                    if (line == null || line.trim().isEmpty() || line.trim().charAt(0) == '#') {
                        continue;
                    }

                    String[] kv = line.split("=", 2);
                    if (kv.length != 2) {
                        continue;
                    }

                    final String key = kv[0].trim();
                    final String value = kv[1].trim();
                    tmp.put(key, value);
                }
            } catch (IOException ex) {
                System.out.println("Can not read parameters !");
                ex.printStackTrace();
            }

            System.out.println("ConfigFile: - Conf file loaded.");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("ERROR : ConfigFile: " + confFile.getAbsolutePath() + "  can not read !! ");
        }

        propertyMap = tmp;

    }

    private File getConfFile() {
        String confFilePath;
        if (StringUtils.isEmpty(clientsConfigFilePath) || clientsConfigFilePath.equals("none")) {
            confFilePath = "";
        } else {
            confFilePath = clientsConfigFilePath + "/";
        }

        confFilePath += clientsConfigFileName;

        return new File(confFilePath);
    }
}
