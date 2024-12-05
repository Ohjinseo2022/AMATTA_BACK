package com.amatta.scheduler.amatta_back.app.socket.service;

import com.amatta.scheduler.amatta_back.app.socket.dto.ChatMessageBaseDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.ChatRoomMasterDTO;
import com.amatta.scheduler.amatta_back.app.socket.dto.request.ChatRoomRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ChatRoomService {
/**
 *      1. 채팅방 생성
 *      2. 채팅방 조회 ( 내가 참여중인 채팅만 조회 or 다른 채팅방 조회기능 추가)
 *          1) 전체조회
 *          2) 검색조건 조회 ( 정렬기준 설정, 채팅방 이름 검색 )
 *      3. 채팅방 참여
 *      4. 채팅방 정보 수정
 *      5. 채팅방 상태변경
 *          IDLE("대기중"),// 처음 생성되고 채팅메세지가 1개도 없는 상태
 *          ACTIVE("활성"),// 채팅을 시작하고난 뒤 상태
 *          INACTIVE("비활성");// 모든 참여자가 나간 상태
 *      TODO: 추가 기능 생각해보기
 */
    // 채팅 방생성
    ChatMessageBaseDTO createChatRoom(HttpServletRequest request , ChatRoomRequestDTO chatRoomRequestDTO);
    ChatRoomMasterDTO saveChatRoom(ChatRoomMasterDTO chatRoomMasterDTO);
    List<ChatRoomMasterDTO> getAllChatRoom(HttpServletRequest request, String secretKey);
}
