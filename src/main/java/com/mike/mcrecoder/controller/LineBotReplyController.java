package com.mike.mcrecoder.controller;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.mike.mcrecoder.services.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@LineMessageHandler
public class LineBotReplyController {

    @Autowired
    private ReplyService replyService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException, URISyntaxException {
        log.info("handleTextMessageEvent event: " + event);
        return replyService.handleTextMessageEvent(event);
    }

    @EventMapping
    public TextMessage handlePostbackEvent(PostbackEvent event){
        log.info("handlePostbackEvent event: " + event);
        return replyService.handlePostbackEvent(event);
    }

    @EventMapping
    public TextMessage handleFollowEvent(FollowEvent followEvent){
        return replyService.handleFollowEvent(followEvent);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        log.info("handleDefaultMessageEvent event: " + event);
    }

}
