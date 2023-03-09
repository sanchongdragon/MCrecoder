package com.mike.mcrecoder.services;

import com.mike.mcrecoder.model.LineUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

@Service
@Slf4j
public class LineApiAccessService {

    private static final String URL = "https://api.line.me/v2/bot/profile/{0}";
    private static final String CHANNEL_ACCESS_TOKEN = "line.bot.channel-token";

    @Autowired
    private RestTemplate restTemplate;

    public LineUserInfoVo accessUserInfo(String userId) throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI(replaceUrl(userId)))
                .header(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + System.getProperty(CHANNEL_ACCESS_TOKEN)
                ).build();
        return restTemplate.exchange(requestEntity, LineUserInfoVo.class).getBody();
    }

    private String replaceUrl(String userId){
        return MessageFormat.format(URL, userId);
    }

}
