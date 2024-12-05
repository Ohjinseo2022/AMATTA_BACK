package com.amatta.scheduler.amatta_back.app.auth.service;

import com.amatta.scheduler.amatta_back.app.auth.dto.requestDTO.LoginReqDTO;
import com.amatta.scheduler.amatta_back.app.auth.dto.responseDTO.LoginResDTO;


public interface OAuth2Service {

    LoginResDTO oauthLogin(String accessToken, LoginReqDTO loginReqDTO);

}
