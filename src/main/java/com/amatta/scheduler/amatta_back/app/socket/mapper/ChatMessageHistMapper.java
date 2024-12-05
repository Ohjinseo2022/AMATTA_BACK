package com.amatta.scheduler.amatta_back.app.socket.mapper;

import com.amatta.scheduler.amatta_back.app.common.mapper.EntityMapper;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatMessageHist;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageHistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ChatMessageHistMapper extends EntityMapper<ChatMessageHistDTO, ChatMessageHist> {

    @Override
    ChatMessageHistDTO toDto(ChatMessageHist entity);

    @Override
    ChatMessageHist toEntity(ChatMessageHistDTO dto);
}
