package com.amatta.scheduler.amatta_back.app.socket.service;


import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatMessageRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface ChatMessageHistService {
    ChatMessageBaseDTO createSendMessage(HttpServletRequest request, String chatRoomId, ChatMessageRequestDTO chatMessageRequestDTO);
}
