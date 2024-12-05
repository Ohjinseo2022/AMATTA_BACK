package com.amatta.scheduler.amatta_back.app.socket.dto.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomRequestDTO {
    private String roomName;
    private List<String> receiverUserIdList;
}
