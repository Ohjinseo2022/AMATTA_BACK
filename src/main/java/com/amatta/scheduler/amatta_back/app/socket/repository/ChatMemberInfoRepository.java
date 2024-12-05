package com.amatta.scheduler.amatta_back.app.socket.repository;

import com.amatta.scheduler.amatta_back.app.socket.domain.ChatMemberInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ChatMemberInfoRepository extends CrudRepository<ChatMemberInfo, String>, JpaSpecificationExecutor<ChatMemberInfo> {
}
