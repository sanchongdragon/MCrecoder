package com.mike.MCrecoder.services;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReplyService {

    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("handleTextMessageEvent event: " + event);
        return new TextMessage(event.getMessage().getText() + " im auto reply bot");
    }

    public void handleDefaultMessageEvent(Event event) {
        log.info("handleDefaultMessageEvent event: " + event);
    }

}
