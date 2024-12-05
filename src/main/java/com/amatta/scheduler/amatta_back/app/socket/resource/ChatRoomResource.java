package com.amatta.scheduler.amatta_back.app.socket.resource;

import com.amatta.scheduler.amatta_back.app.common.exception.BadRequestAlertException;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatRoomRequestDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatErrorDTO;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatRoomService;
import com.amatta.scheduler.amatta_back.app.user.service.UserMasterService;
import com.amatta.scheduler.amatta_back.utils.jwt.TokenValues;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="실시간 채팅방 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-room")
public class ChatRoomResource {
    private final TokenValues tokenValues;
    String ENTITY_NAME = "ChatRoomResource";
    private final ChatRoomService chatRoomService;
    private final UserMasterService userMasterService;
    private final Logger log = LoggerFactory.getLogger(ChatRoomResource.class);

    @PostMapping("/create")
    public ResponseEntity<ChatMessageBaseDTO> createChatRoom(HttpServletRequest request, ChatRoomRequestDTO chatRoomRequestDTO){
        /**
         * 채팅 방 생성시 필요 정보.
         * 1. 생성 주체인 회원의 정보 // 헤더에 있는 엑세스 토큰 검증을 통해 유저 정보 추출
         * 2. 채팅방이름 // 없을시 자동생성 필요 - 1대1 채팅일경우 채팅방 이름대신 서로의 이름이 노출되게 해야함
         * 3. 채팅 대상 회원 ID 정보 List 형태 - 1대1 or 단체 채팅일 경우를 고려함.
         * 4. 채팅방 테이블 데이터 생성 후 해당 정보에 맞게 채팅방 참여자 테이블에 정보 같이 추가해줘야함!
         * */
        try{
            ChatMessageBaseDTO result = chatRoomService.createChatRoom(request,chatRoomRequestDTO);
            if(result instanceof ChatErrorDTO){

                HttpStatus errorCode = ((ChatErrorDTO) result).getErrorCode();
                log.info("instanceof ChatErrorMessageDTO : {}", errorCode);
                return ResponseEntity.status(errorCode).body(result);
            }
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            throw new BadRequestAlertException("Unintended error",ENTITY_NAME,e.getMessage());
        }
    }
    /**
     * 채팅방 전체 조회 서비스 개발
     * 생성시간 기준 내림차순 정렬 필요
     * 1. 현재 로그인 정보 조회
     * 2. 로그인한 사용자가 참여중인 채팅방
     *      - 채탕방 ChatRoomStatusCode 가 IDLE , ACTIVE 인거만 조회
     * 3. 대화내용이 있다면 생성날짜기준 가장 최근 1건 리스트로 받아오기 TODO: 추후 상세 조회시 별도의 조회 로직 개발
     *      ->> 마지막 채팅 메시지 추가될때마다 업데이트 처리후 조인 걸어놨음 // 인피티니 스크롤 구현을 위함.
     */
    @GetMapping("/all")
    public ResponseEntity<ChatMessageBaseDTO> getAllChatRoom(HttpServletRequest request){
        List<ChatRoomMasterDTO>  chatRoomList = chatRoomService.getAllChatRoom(request,tokenValues.secretKey());
        return null;
    }
}
