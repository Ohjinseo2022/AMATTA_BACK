package com.amatta.scheduler.amatta_back.app.auth.dto.kakaoDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoOauthUserProfileResponse(
        String name,
        String email,
        @JsonProperty("kakao_account.email") String profileImageUri
) {
}
