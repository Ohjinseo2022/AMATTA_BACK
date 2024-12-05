package com.amatta.scheduler.amatta_back.app.socket.repository;

import com.amatta.scheduler.amatta_back.app.socket.domain.ChatRoomMaster;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomMasterRepository extends CrudRepository<ChatRoomMaster, String>, JpaSpecificationExecutor<ChatRoomMaster> {
/**
 * 1. 채팅룸 저장
 * 2. 채팅룸 업데이트
 * 3. 채팅룸 리스트 조회 - 정렬기준 지정
 */
 ChatRoomMaster findAllByRoomNameContaining(String roomName);
}
