package com.amatta.scheduler.amatta_back.app.socket.dto;

import com.amatta.scheduler.amatta_back.app.common.dto.AbstractAuditingDTO;
import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatParticipationStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatRoomMaster;
import com.amatta.scheduler.amatta_back.app.user.domain.UserMaster;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Schema(description = "채팅 참여 회원 정보 테이블")
@Data
@EqualsAndHashCode(callSuper = true,of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMemberInfoDTO  extends AbstractAuditingDTO<String> implements Serializable {


    @Size(max=36)
    @Schema(description = "채팅 참여 회원 정보 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String id;

    @Size(max=36)
    @Schema(description = "채팅방 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String chatRoomMasterId;

    @Schema(description = "채팅방 고유 ID")
    private ChatRoomMaster chatRoomMaster;

    @Size(max=36)
    @Schema(description = "회원 관리 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String userMasterId;

    @Schema(description = "회원 관리 고유 ID")
    private UserMaster userMaster;

    @Size(max = 100)
    @Schema(description = "채팅방 참여 상태 코드")
    private ChatParticipationStatusCode chatParticipationStatusCode;
}
