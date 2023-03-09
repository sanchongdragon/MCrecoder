package com.mike.mcrecoder.services;

import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.postback.PostbackContent;
import com.linecorp.bot.model.message.TextMessage;
import com.mike.mcrecoder.model.LineUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
public class ReplyService {

    @Autowired
    private GoogleSheetsService googleSheetsService;
    @Autowired
    private LineApiAccessService lineApiAccessService;
    private static final String GREETING = "感謝妳加入McRecorder，接下來可以透過下方的小視窗來記錄或查詢Mc。";
    private static final String QUERY_PREVIOUS = "開始查詢前一次";
    private static final String QUERY_CYCLE = "開始查詢週期";
    private static final String CALC_NEXT = "開始計算下一次";
    private static final String DATE = "date";
    private static final String DUCK = "duck";
    private static final int MIN_CYCLE = 25;

    /**
     * handle text message
     * @param event text message
     * @return {@link TextMessage}
     */
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException, URISyntaxException {
        log.info("handleTextMessageEvent event: " + event);
//        googleSheetsService.queryPrevious();
//        log.info("Line UserId: {}", getUserName(event.getSource().getUserId()));
        LineUserInfoVo userInfoVo = lineApiAccessService.accessUserInfo(event.getSource().getUserId());
//        log.info(userInfoVo.toString());
//        googleSheetsService.getAllSheets();
        log.info(googleSheetsService.queryPrevious(userInfoVo.getDisplayName()));
        return new TextMessage(event.getMessage().getText() + " im auto reply bot");
    }

    /**
     * handle postback event
     * @param postbackEvent postback event
     * @return {@link TextMessage}
     */
    public TextMessage handlePostbackEvent(PostbackEvent postbackEvent){
        PostbackContent content = postbackEvent.getPostbackContent();
        String data = content.getData();
        String returnMsg = String.format("your select date is [%s]", content.getParams().get(DATE));
        return new TextMessage(returnMsg);
    }

    /**
     * handle follow event
     * @param followEvent follow event
     * @return {@link TextMessage}
     */
    public TextMessage handleFollowEvent(FollowEvent followEvent){
        return new TextMessage(GREETING);
    }

    public void handleDefaultMessageEvent(Event event) {
        log.info("handleDefaultMessageEvent event: " + event);
    }

}
