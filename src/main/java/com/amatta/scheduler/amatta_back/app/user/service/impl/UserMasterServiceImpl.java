package com.amatta.scheduler.amatta_back.app.user.service.impl;

import com.amatta.scheduler.amatta_back.app.auth.dto.OauthUserProfileResponse;
import com.amatta.scheduler.amatta_back.app.auth.dto.requestDTO.LoginReqDTO;
import com.amatta.scheduler.amatta_back.app.auth.service.AuthTransactionalService;
import com.amatta.scheduler.amatta_back.app.common.enumeration.SocialTypeCode;
import com.amatta.scheduler.amatta_back.app.user.domain.UserMaster;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;
import com.amatta.scheduler.amatta_back.app.user.mapper.UserMaterMapper;
import com.amatta.scheduler.amatta_back.app.user.repository.UserMasterRepository;
import com.amatta.scheduler.amatta_back.app.user.service.UserMasterService;
import com.amatta.scheduler.amatta_back.utils.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserMasterServiceImpl implements UserMasterService {
    private final UserMasterRepository userMasterRepository;
    private final UserMaterMapper userMaterMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthTransactionalService authTransactionalService;
    public UserMasterServiceImpl(UserMasterRepository userMasterRepository, UserMaterMapper userMaterMapper, JwtTokenProvider jwtTokenProvider, AuthTransactionalService authTransactionalService) {
        this.userMasterRepository = userMasterRepository;
        this.userMaterMapper = userMaterMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authTransactionalService = authTransactionalService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserMasterDTO createOrPartialUpdateUserMaster(OauthUserProfileResponse profileResponse , LoginReqDTO loginReqDTO) {
        Optional<UserMaster> existingUserMAster = userMasterRepository.findByEmail(profileResponse.email());
    /**
     * TODO 해결 해야할 문제점.... 소셜로그인 진행시 구글계정, 카카오계정, 네이버 계정 이메일 정보가 다를 수 있음....
     * */
        UserMasterDTO newUserDTO = new UserMasterDTO();
        String socialTypeCode = SocialTypeCode.from(loginReqDTO.getSocialType());
        newUserDTO.setEmail(profileResponse.email());
        newUserDTO.setName(profileResponse.name());
        newUserDTO.setProfileImageUri(profileResponse.profileImageUri());
        if(!socialTypeCode.isEmpty()){
            if (SocialTypeCode.GOOGLE.getValue().equals(socialTypeCode))   newUserDTO.setGoogleSignIn(true);
            if (SocialTypeCode.KAKAO.getValue().equals(socialTypeCode))    newUserDTO.setKakaoSignIn(true);
            if (SocialTypeCode.NAVER.getValue().equals(socialTypeCode))    newUserDTO.setNaverSignIn(true);
        };
        if (existingUserMAster.isPresent()) {

            return existingUserMAster.map((existingUser) -> {

                newUserDTO.setId(existingUser.getId());
                userMaterMapper.partialUpdate(existingUser, newUserDTO);
                return existingUser;
            }).map(userMasterRepository::save).map(userMaterMapper::toDto).get();
        }else{
//            UserMasterDTO newUserDTO = new UserMasterDTO();
//            newUserDTO.setEmail(profileResponse.email());
//            newUserDTO.setName(profileResponse.name());
//            newUserDTO.setProfileImageUri(profileResponse.profileImageUri());
            return userMaterMapper.toDto(userMasterRepository.save(userMaterMapper.toEntity(newUserDTO)));
        }
    }

    @Override
    public UserMasterDTO getUserMasterById(String userId) {
        UserMaster userMaster = userMasterRepository.findById(userId).orElse(null);
        if(userMaster != null){
            return userMaterMapper.toDto(userMaster);
        }
        return null;
    }

    @Override
    public UserMasterDTO isValidTokenCheckToGetUserMaster(HttpServletRequest request, String secretKey) {
        String accessToken = authTransactionalService.extractTokenFromRequest(request);
        Boolean isValidate = jwtTokenProvider.validateToken(accessToken);
        if(isValidate){
            String userId = jwtTokenProvider.getSubject(accessToken, secretKey);
            UserMasterDTO userMasterDTO = this.getUserMasterById(userId);
            return userMasterDTO;
        }
        return null;
    }
}
