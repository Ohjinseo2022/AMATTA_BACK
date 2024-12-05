package com.amatta.scheduler.amatta_back.app.auth.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDTO {
    String accessToken;
    String refreshToken;
}
