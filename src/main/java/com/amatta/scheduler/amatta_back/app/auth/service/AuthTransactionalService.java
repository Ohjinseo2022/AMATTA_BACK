package com.amatta.scheduler.amatta_back.app.auth.service;

import com.amatta.scheduler.amatta_back.app.token.dto.TokenManagementMaterDTO;
import com.amatta.scheduler.amatta_back.app.user.dto.UserMasterDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthTransactionalService {

    String extractTokenFromRequest(HttpServletRequest request);

    TokenManagementMaterDTO createAccessTokenAndRefreshToken(UserMasterDTO userMasterDTO);
}
