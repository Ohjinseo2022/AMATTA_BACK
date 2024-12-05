package com.amatta.scheduler.amatta_back.app.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OauthUserProfileResponse(
        String name,
        String email,
        @JsonProperty("picture") String profileImageUri
) {
}
