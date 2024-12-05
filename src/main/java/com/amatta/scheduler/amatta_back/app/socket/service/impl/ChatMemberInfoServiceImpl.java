package com.amatta.scheduler.amatta_back.app.socket.service.impl;

import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatParticipationStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMemberInfoDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;
import com.amatta.scheduler.amatta_back.app.socket.mapper.ChatMemberInfoMapper;
import com.amatta.scheduler.amatta_back.app.socket.repository.ChatMemberInfoRepository;
import com.amatta.scheduler.amatta_back.app.socket.service.ChatMemberInfoService;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;

import org.springframework.stereotype.Service;

@Service
public class ChatMemberInfoServiceImpl implements ChatMemberInfoService {
    private final ChatMemberInfoMapper chatMemberInfoMapper;
    private final ChatMemberInfoRepository chatMemberInfoRepository;
    public ChatMemberInfoServiceImpl(ChatMemberInfoMapper chatMemberInfoMapper, ChatMemberInfoRepository chatMemberInfoRepository) {
        this.chatMemberInfoMapper = chatMemberInfoMapper;
        this.chatMemberInfoRepository = chatMemberInfoRepository;
    }

    @Override
    public ChatMemberInfoDTO saveMemberInfo(ChatRoomMasterDTO chatRoomMasterDTO, UserMasterDTO userMasterDTO, ChatParticipationStatusCode chatParticipationStatusCode) {
        ChatMemberInfoDTO chatMemberOwnerDTO = new ChatMemberInfoDTO();
//        chatMemberOwnerDTO.setChatRoomMaster(chatRoomMasterDTO);
        chatMemberOwnerDTO.setChatRoomMasterId(chatRoomMasterDTO.getId());
        chatMemberOwnerDTO.setUserMasterId(userMasterDTO.getId());
//        chatMemberOwnerDTO.setUserMaster(userMasterDTO);
        chatMemberOwnerDTO.setChatParticipationStatusCode(chatParticipationStatusCode);
        return chatMemberInfoMapper.toDto(chatMemberInfoRepository.save(chatMemberInfoMapper.toEntity(chatMemberOwnerDTO)));
    }
}
