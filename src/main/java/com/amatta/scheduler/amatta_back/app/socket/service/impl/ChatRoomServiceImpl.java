package com.amatta.scheduler.amatta_back.app.socket.service.impl;

import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatParticipationStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMemberInfoDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatRoomRequestDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatErrorDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatRoomSuccessResponseDTO;
import com.amatta.scheduler.amatta_back.app.socket.mapper.ChatRoomMasterMapper;
import com.amatta.scheduler.amatta_back.app.socket.repository.ChatRoomMasterRepository;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatMemberInfoService;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatRoomService;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;
import com.amatta.scheduler.amatta_back.app.user.service.UserMasterService;
import com.amatta.scheduler.amatta_back.utils.DateUtil;
import com.amatta.scheduler.amatta_back.utils.common.TsidUtil;
import com.amatta.scheduler.amatta_back.utils.jwt.TokenValues;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private static final Logger log = LoggerFactory.getLogger(ChatRoomServiceImpl.class);
    private final UserMasterService userMasterService;
    private final TokenValues tokenValues;
    private final ChatRoomMasterRepository chatRoomMasterRepository;
    private final ChatRoomMasterMapper chatRoomMasterMapper;
    private final ChatMemberInfoService chatMemberInfoService;
    public ChatRoomServiceImpl(UserMasterService userMasterService, TokenValues tokenValues, ChatRoomMasterRepository chatRoomMasterRepository, ChatRoomMasterMapper chatRoomMasterMapper, ChatMemberInfoService chatMemberInfoService) {
        this.userMasterService = userMasterService;
        this.tokenValues = tokenValues;
        this.chatRoomMasterRepository = chatRoomMasterRepository;
        this.chatRoomMasterMapper = chatRoomMasterMapper;
        this.chatMemberInfoService = chatMemberInfoService;
    }

    @Override
    public ChatMessageBaseDTO createChatRoom(HttpServletRequest request, ChatRoomRequestDTO chatRoomRequestDTO) {
        UserMasterDTO userMasterDTO =  userMasterService.isValidTokenCheckToGetUserMaster(request,tokenValues.secretKey());
        try{
            if(ObjectUtils.isEmpty(userMasterDTO)){
                log.info("로그인 정보가 유효하지 않음");
                return new ChatErrorDTO("unauthorized 401", HttpStatus.UNAUTHORIZED);
            }
            ChatRoomMasterDTO newChatRoomMaster = new ChatRoomMasterDTO();
            if(ObjectUtils.isEmpty(chatRoomRequestDTO.getRoomName())){
                newChatRoomMaster.setRoomName("자동생성 RoomNAME"+ TsidUtil.getId());
            }else{
                newChatRoomMaster.setRoomName(chatRoomRequestDTO.getRoomName());
            }
            ChatRoomMasterDTO idleRoomDTO = this.saveChatRoom(newChatRoomMaster);
            if(ObjectUtils.isEmpty(idleRoomDTO)){
                log.info("채팅방 생성 실패");
                return new ChatErrorDTO("create Room failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ChatMemberInfoDTO chatMemberOwnerDTO = chatMemberInfoService.saveMemberInfo(idleRoomDTO,userMasterDTO, ChatParticipationStatusCode.CHAT_PENDING);
            List<ChatMemberInfoDTO> ListMemberInfoDTO= chatRoomRequestDTO.getReceiverUserIdList().stream().map((id)->{
                   UserMasterDTO userDTO =  userMasterService.getUserMasterById(id);
                   ChatMemberInfoDTO newMemberInfoDTO = chatMemberInfoService.saveMemberInfo(idleRoomDTO,userDTO,ChatParticipationStatusCode.CHAT_PENDING);
                   return newMemberInfoDTO;
            }).toList();
            return new ChatRoomSuccessResponseDTO(idleRoomDTO.getId(),
                    idleRoomDTO.getRoomName(),
                    idleRoomDTO.getChatRoomStatusCode(),
                    DateUtil.instantToStringDate(idleRoomDTO.getCreatedDate(),"YYYY-MM-DD"),
                    DateUtil.instantToStringDate(idleRoomDTO.getLastModifiedDate(),"YYYY-MM-DD")
            );
        }catch (Exception e){
            return new ChatErrorDTO("Exception create Room failed : " + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ChatRoomMasterDTO saveChatRoom(ChatRoomMasterDTO chatRoomMasterDTO) {
        log.info("saveChatRoom : {} ", chatRoomMasterDTO.getRoomName());
        return chatRoomMasterMapper.toDto(chatRoomMasterRepository.save(chatRoomMasterMapper.toEntity(chatRoomMasterDTO)));
    }

    @Override
    public List<ChatRoomMasterDTO> getAllChatRoom(HttpServletRequest request, String secretKey) {
        /**
         * 1.
         */
        return null;
    }

}
