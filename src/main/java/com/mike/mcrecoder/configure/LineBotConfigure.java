package com.mike.mcrecoder.configure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Configuration
@Slf4j
public class LineBotConfigure {

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Load linebot-config.json in Classpath ");
        try{
            Map<String, String> configMap = objectMapper.readValue(
                    new ClassPathResource("static/linebot-config.json").getFile(),
                    new TypeReference<>() {
                    }
            );

            configMap.forEach(System::setProperty);
        }catch(IOException ioE){
            log.error("Fail to Load linebot-config.json in Static Folder");
        }

    }

}
