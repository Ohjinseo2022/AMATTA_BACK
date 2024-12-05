package com.amatta.scheduler.amatta_back.app.auth.oauth2;

import com.amatta.scheduler.amatta_back.app.auth.dto.OauthUserProfileResponse;
import com.amatta.scheduler.amatta_back.app.auth.dto.kakaoDTO.KakaoOauthDTO;

public interface OAuthWebClient {
    OauthUserProfileResponse requestGoogleOauthUserProfile(String accessToken);

    KakaoOauthDTO requestKakaoOauthUserProfile(String accessToken);
}
