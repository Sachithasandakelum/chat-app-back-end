package lk.ijse.dep11.app.wscontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep11.app.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatWSController extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;

    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();
    public ChatWSController() {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionList.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageTo messageObj = mapper.readValue(message.getPayload(), MessageTo.class);
        Set<ConstraintViolation<MessageTo>> violations = validatorFactoryBean.getValidator().validate(messageObj);

        try {
            if(violations.isEmpty()){
                for (WebSocketSession webSocketSession:webSocketSessionList){
                    if(webSocketSession==session) continue;
                    webSocketSession.sendMessage(new TextMessage(message.getPayload()));
                }
            }else {
                session.sendMessage(new TextMessage("Invalid message schema"));
            }
        }catch (Exception e){
            session.sendMessage(new TextMessage("Invalid JSON"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionList.remove(session);
    }
}
