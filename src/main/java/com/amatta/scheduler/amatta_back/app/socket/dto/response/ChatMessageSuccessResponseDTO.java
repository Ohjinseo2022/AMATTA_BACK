package com.amatta.scheduler.amatta_back.app.socket.dto.response;

import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageSuccessResponseDTO extends ChatMessageBaseDTO {
    private String id;
    private String chatRoomId;
    private String sendMessageText;
    private Resource file;
    private String createdDate;
    private String lastModifiedDate;
}
