package com.amatta.scheduler.amatta_back.app.socket.dto;

import com.amatta.scheduler.amatta_back.app.common.domain.AttachDocMaster;
import com.amatta.scheduler.amatta_back.app.common.dto.AbstractAuditingDTO;
import com.amatta.scheduler.amatta_back.app.socket.domain.ChatRoomMaster;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Schema(description = "채팅 메세지 이력 테이블")
@Data @EqualsAndHashCode(callSuper = true,of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageHistDTO extends AbstractAuditingDTO<String> implements Serializable {

    @Size(max=36)
    @Schema(description = "채팅 이력 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String id;

    @Size(max=36)
    @Schema(description = "채팅방 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String chatRoomId;

    @Schema(description = "채팅방 정보 테이블",requiredMode= Schema.RequiredMode.REQUIRED)
    private ChatRoomMaster chatRoomMaster;

    @Size(max=36)
    @Schema(description = "회원 고유 ID",requiredMode= Schema.RequiredMode.REQUIRED)
    private String userMasterId;
    /**
     * TODO: attach_doc_master 테이블 생성 후 조인 설정 잡기
     */
    @Size(max=36)
    @Schema(description = "첨부 문서 정보 고유 ID")
    private String attachDocId;

    @Schema(description = "첨부 문서 정보 테이블")
    private AttachDocMaster attachDocMaster;

    @Size(max=5000)
    @Schema(description = "채팅 메세지 전송 내용")
    private String sendMessageText;

    @Schema(description = "최초 생성 일시")
    private Instant createdDate;

    @Schema(description = "마지막 변경 일시")
    private Instant lastModifiedDate;

}
