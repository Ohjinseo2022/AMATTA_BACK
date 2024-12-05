package com.amatta.scheduler.amatta_back.app.socket.dto;


import com.amatta.scheduler.amatta_back.app.common.dto.AbstractAuditingDTO;
import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatRoomStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatMessageHist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Schema(description = "채팅방 정보 테이블")
@Data @EqualsAndHashCode(callSuper = true,of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomMasterDTO extends AbstractAuditingDTO<String> implements Serializable {

    @Size(max=36)
    @Schema(description = "채팅방 정보 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String id;

    @Size(max=500)
    @Schema(description = "채팅방 이름",requiredMode= Schema.RequiredMode.REQUIRED)
    private String roomName;

    /**
     *  기본값 IDLE 채팅 시작 후 ACTIVE , 참여자가 1 이되는순간 INACTIVE 상태로 변경
     * */
    @Size(max=100)
    @Schema(description = "채팅방 상태코드")
    private ChatRoomStatusCode chatRoomStatusCode= ChatRoomStatusCode.IDLE;

    @Size(max=36)
    @Schema(description = "마지막 채팅 메세지 고유 ID")
    private String lastChatMessageId;

    @Schema(description = "마지막 채팅 메세지 고유 ID")
    private ChatMessageHist chatMessageHist;

    @Schema(description = "최초 생성 일시")
    private Instant createdDate;

    @Schema(description = "마지막 변경 일시")
    private Instant lastModifiedDate;
}
