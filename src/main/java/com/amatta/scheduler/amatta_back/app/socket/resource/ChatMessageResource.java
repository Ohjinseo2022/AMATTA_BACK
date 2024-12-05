package com.amatta.scheduler.amatta_back.app.socket.resource;


import com.amatta.scheduler.amatta_back.app.common.exception.BadRequestAlertException;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatMessageRequestDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatErrorDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatMessageSuccessResponseDTO;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatMessageHistService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name="실시간 채팅 웹 소켓")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-message")
public class ChatMessageResource {
    String ENTITY_NAME = "ChatResource";
    private final ChatMessageHistService chatMessageHistService;
    private final Logger log = LoggerFactory.getLogger(ChatMessageResource.class);

    @MessageMapping("/chat/rooms/{roomId}/send")
    @SendTo("/topic/public/rooms/{roomId}")
    public ResponseEntity<ChatMessageBaseDTO> sendRealTimeMessage(
            HttpServletRequest request,
            @PathVariable(value = "roomId") String roomId,
            @RequestBody ChatMessageRequestDTO chatMessageRequestDTO,
            //37 라인은 필요 업을수도 있음 !
            Principal principal ) {
        //메세지 정보 히스트 테이블에 저장 후 넘겨주기.
        ChatMessageBaseDTO chatMessageBaseDTO = chatMessageHistService.createSendMessage(request,roomId,chatMessageRequestDTO);
        // 에러 발생시 ChatErrorMessageDTO 클래스형태로 넘어옴. 해당 형색일경우 에러 코드와 메세지를 바디에 담아서 리턴
        try{
            if(chatMessageBaseDTO instanceof ChatErrorDTO){
                HttpStatus errorCode = ((ChatErrorDTO) chatMessageBaseDTO).getErrorCode();
                log.info("instanceof ChatErrorMessageDTO : {}", errorCode);
                return ResponseEntity.status(errorCode).body(chatMessageBaseDTO);
            }
            // 61~65 라인까지 if 문이 필요없을수도 있음 좀더 생각해보기
            if(chatMessageBaseDTO instanceof ChatMessageSuccessResponseDTO){
                log.info("success full create Chat Message : {}", ((ChatMessageSuccessResponseDTO) chatMessageBaseDTO).getId());
                return ResponseEntity.ok().body(chatMessageBaseDTO);
            }
            return ResponseEntity.ok().body(chatMessageBaseDTO);
        }catch (Exception e){
            throw new BadRequestAlertException("Unintended error",ENTITY_NAME,e.getMessage());
        }
    }

}
