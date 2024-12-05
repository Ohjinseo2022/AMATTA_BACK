package com.amatta.scheduler.amatta_back.app.socket.repository;

import com.amatta.scheduler.amatta_back.app.socket.domain.ChatMessageHist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageHistRepository extends CrudRepository<ChatMessageHist, String>, JpaSpecificationExecutor<ChatMessageHist> {
    /**
     * @param chatRoomMasterId
     * @return 채팅 내역 생성 시간 기준으로 조회, 페이징처리 -> 추후 인피니티 스크롤 형태로 로직 수정해야함
     */
    List<ChatMessageHist> findAllByChatRoomMaster_IdOrderByCreatedDateDesc(String chatRoomMasterId);

}
