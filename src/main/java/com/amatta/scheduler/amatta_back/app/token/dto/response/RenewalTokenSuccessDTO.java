package com.amatta.scheduler.amatta_back.app.token.dto.response;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenewalTokenSuccessDTO extends ResponseTokenManagementBaseDTO{
    private String accessToken;
    private String refreshToken;
}
