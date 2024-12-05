package com.amatta.scheduler.amatta_back.app.socket.domain;


import com.amatta.scheduler.amatta_back.app.common.domain.AbstractAuditingEntity;
import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatParticipationStatusCode;
import com.amatta.scheduler.amatta_back.app.user.domain.UserMaster;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true,of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name="tb_chat_member_info")
@Comment("채팅 참여 회원 정보 테이블")
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChatMemberInfo extends AbstractAuditingEntity<String> implements Serializable {
    @Comment("채팅 참여 회원 정보 고유 ID")
    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id",length = 36,nullable = false,unique = true)
    private String id;

    @Comment("채팅방 고유 ID")
    @Size(max=36)
    @Column(name="chat_room_master_id",length=36,nullable = false)
    private String chatRoomMasterId;

    @ManyToOne
    @JoinColumn(name = "chat_room_master_id",insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_chatmemberinfo_chatroommaster"))
    private ChatRoomMaster chatRoomMaster;

    @Comment("회원 관리 고유 ID")
    @Size(max = 36)
    @Column(name="user_master_id",length = 36 ,updatable = false)
    private String userMasterId;

    @Comment("회원 관리 고유 ID")
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_master_id" , insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_tokenmanagementmaster_usermaster"))
    private UserMaster userMaster;

    @Comment("채팅방 참여 상태 코드")
    @Size(max = 100)
    @Column(name="participation_status_code")
    private ChatParticipationStatusCode chatParticipationStatusCode;
}
