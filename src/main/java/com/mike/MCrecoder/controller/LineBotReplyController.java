package com.mike.MCrecoder.controller;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.mike.MCrecoder.services.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@Slf4j
@LineMessageHandler
public class LineBotReplyController {

    @Autowired
    private ReplyService replyService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws GeneralSecurityException, IOException {
        log.info("handleTextMessageEvent event: " + event);
        return replyService.handleTextMessageEvent(event);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        log.info("handleDefaultMessageEvent event: " + event);
    }

}
