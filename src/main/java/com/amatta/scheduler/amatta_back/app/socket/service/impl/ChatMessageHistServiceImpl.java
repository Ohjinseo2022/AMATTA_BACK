package com.amatta.scheduler.amatta_back.app.socket.service.impl;

import com.amatta.scheduler.amatta_back.app.socket.domain.ChatRoomMaster;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageHistDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatMessageRequestDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatErrorDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.response.ChatMessageSuccessResponseDTO;
import com.amatta.scheduler.amatta_back.app.socket.mapper.ChatMessageHistMapper;
import com.amatta.scheduler.amatta_back.app.socket.repository.ChatMessageHistRepository;
import com.amatta.scheduler.amatta_back.app.socket.repository.ChatRoomMasterRepository;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatMessageHistService;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;
import com.amatta.scheduler.amatta_back.app.user.service.UserMasterService;
import com.amatta.scheduler.amatta_back.utils.DateUtil;
import com.amatta.scheduler.amatta_back.utils.jwt.TokenValues;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.Optional;

@Service
public class ChatMessageHistServiceImpl implements ChatMessageHistService {
    private static final Logger log = LoggerFactory.getLogger(ChatMessageHistServiceImpl.class);
    private final ChatRoomMasterRepository chatRoomMasterRepository;
    private final ChatMessageHistRepository chatMessageHistRepository;
    private final UserMasterService userMasterService;
    private final ChatMessageHistMapper chatMessageHistMapper;
    private final TokenValues tokenValues;


    public ChatMessageHistServiceImpl(ChatRoomMasterRepository chatRoomMasterRepository, ChatMessageHistRepository chatMessageHistRepository, UserMasterService userMasterService, ChatMessageHistMapper chatMessageHistMapper, TokenValues tokenValues) {
        this.chatRoomMasterRepository = chatRoomMasterRepository;
        this.chatMessageHistRepository = chatMessageHistRepository;
        this.userMasterService = userMasterService;
        this.chatMessageHistMapper = chatMessageHistMapper;
        this.tokenValues = tokenValues;
    }

    @Override
    public ChatMessageBaseDTO createSendMessage(HttpServletRequest request, String chatRoomId, ChatMessageRequestDTO chatMessageRequestDTO) {
        /**
         * 1. 메세지 파라미터에 담겨있는 룸아이디 정보로 채팅룸 상태가 유요한지 체크,
         * 2. 채팅룸 상태가 유효하다면 해당 정보 기반으로 채팅메세지정보를 DB에 저장.
         * 3. 클라이언트엔 메세지 정보를 가지고있는 ID 정보만 리턴
         */
        UserMasterDTO userMasterDTO =  userMasterService.isValidTokenCheckToGetUserMaster(request,tokenValues.secretKey());
        if(ObjectUtils.isEmpty(userMasterDTO)){
            log.info("로그인 정보가 유효하지 않음");
            return new ChatErrorDTO("unauthorized 401",HttpStatus.UNAUTHORIZED);
        }else{
          Optional<ChatRoomMaster> chatRoomMaster =  chatRoomMasterRepository.findById(chatRoomId);
          if(chatRoomMaster.isPresent()){
//              Optional<ChatMessageHistDTO> createMessageDto =
//
              ChatMessageHistDTO newMessageDto = new ChatMessageHistDTO();
              newMessageDto.setChatRoomId(chatRoomMaster.get().getId());
              if(!ObjectUtils.isEmpty(chatMessageRequestDTO.getSendMessageText())){
                  newMessageDto.setSendMessageText(chatMessageRequestDTO.getSendMessageText());
              }
              if(!ObjectUtils.isEmpty(chatMessageRequestDTO.getFile())){
                  // TODO: MinIO 서버 구축 후 저장로직 새로 만들어야함.
              }
              newMessageDto.setUserMasterId(userMasterDTO.getId());
              ChatMessageHistDTO chatMessageHistDTO = chatMessageHistMapper.toDto(chatMessageHistRepository.save(chatMessageHistMapper.toEntity(newMessageDto)));
              if(!ObjectUtils.isEmpty(chatMessageHistDTO.getId())){
                  return new ChatMessageSuccessResponseDTO(chatMessageHistDTO.getId(),
                          chatMessageHistDTO.getChatRoomId(),
                          chatMessageHistDTO.getSendMessageText(),
                          null,
                          DateUtil.instantToStringDate(chatMessageHistDTO.getCreatedDate(),"YYYY-MM-DD"),
                          DateUtil.instantToStringDate(chatMessageHistDTO.getLastModifiedDate(),"YYYY-MM-DD"));
              }
              return new ChatErrorDTO("failed create message",HttpStatus.INTERNAL_SERVER_ERROR);

          }else{
              log.info("채팅룸이 존재하지 않음!");
              return new ChatErrorDTO("Chat Room does not exist",HttpStatus.INTERNAL_SERVER_ERROR);
          }

        }
    }
}
