package com.amatta.scheduler.amatta_back.app.socket.mapper;

import com.amatta.scheduler.amatta_back.app.common.mapper.EntityMapper;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatMemberInfo;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMemberInfoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ChatMemberInfoMapper extends EntityMapper<ChatMemberInfoDTO, ChatMemberInfo> {
    @Override
    ChatMemberInfoDTO toDto(ChatMemberInfo entity);

    @Override
    ChatMemberInfo toEntity(ChatMemberInfoDTO dto);
}
