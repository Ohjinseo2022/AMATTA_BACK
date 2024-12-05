package com.amatta.scheduler.amatta_back.app.socket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageRequestDTO {
    private String chatRoomId;
    private String receiverName;
    private String sendMessageText;
    private MultipartFile file; // 미지 , 파일 등등 다양할수 있음
}
