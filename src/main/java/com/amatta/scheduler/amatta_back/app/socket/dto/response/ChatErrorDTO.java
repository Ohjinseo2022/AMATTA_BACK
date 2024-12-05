package com.amatta.scheduler.amatta_back.app.socket.dto.response;

import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatErrorDTO extends ChatMessageBaseDTO {
    private String massage;
    private HttpStatus errorCode;

}
