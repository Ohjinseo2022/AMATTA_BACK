package com.amatta.scheduler.amatta_back.app.socket.dto.response;

import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatRoomStatusCode;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomSuccessResponseDTO extends ChatMessageBaseDTO {
    private String id;
    private String roomName;
    private ChatRoomStatusCode chatRoomStatusCode;
    private String createdDate;
    private String lastModifiedDate;
}
