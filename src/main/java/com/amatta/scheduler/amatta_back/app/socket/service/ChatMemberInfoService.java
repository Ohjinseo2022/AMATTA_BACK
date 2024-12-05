package com.amatta.scheduler.amatta_back.app.socket.service;

import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatParticipationStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMemberInfoDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;

public interface ChatMemberInfoService {
    ChatMemberInfoDTO saveMemberInfo(ChatRoomMasterDTO chatRoomMasterDTO, UserMasterDTO userMasterDTO, ChatParticipationStatusCode chatParticipationStatusCode);
}
