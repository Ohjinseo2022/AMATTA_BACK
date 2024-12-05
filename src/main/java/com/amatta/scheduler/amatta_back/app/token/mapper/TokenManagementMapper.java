package com.amatta.scheduler.amatta_back.app.token.mapper;

import com.amatta.scheduler.amatta_back.app.common.mapper.EntityMapper;
import com.amatta.scheduler.amatta_back.app.token.domain.TokenManagementMaster;
import com.amatta.scheduler.amatta_back.app.token.dto.TokenManagementMaterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TokenManagementMapper extends EntityMapper<TokenManagementMaterDTO, TokenManagementMaster> {
    @Override
    @Mapping(target = "userMasterDTO" ,source = "userMaster")
    @Mapping(target = "userMasterDTO.name", source = "userMaster.userName")
    TokenManagementMaterDTO toDto(TokenManagementMaster tokenManagementMaster);

    @Override
    @Mapping(target = "userMaster" ,source = "userMasterDTO")
    @Mapping(target = "userMaster.userName", source = "userMasterDTO.name")
    TokenManagementMaster toEntity(TokenManagementMaterDTO tokenManagementMaterDTO);
}
