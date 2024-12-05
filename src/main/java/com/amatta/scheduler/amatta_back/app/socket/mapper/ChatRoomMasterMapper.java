package com.amatta.scheduler.amatta_back.app.socket.mapper;

import com.amatta.scheduler.amatta_back.app.common.mapper.EntityMapper;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatRoomMaster;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ChatRoomMasterMapper extends EntityMapper<ChatRoomMasterDTO, ChatRoomMaster> {
    @Override
    ChatRoomMasterDTO toDto(ChatRoomMaster entity);

    @Override
    ChatRoomMaster toEntity(ChatRoomMasterDTO chatRoomMasterDTO);
}
