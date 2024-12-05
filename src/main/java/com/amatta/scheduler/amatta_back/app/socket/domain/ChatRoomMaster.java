package com.amatta.scheduler.amatta_back.app.socket.domain;

import com.amatta.scheduler.amatta_back.app.common.domain.AbstractAuditingEntity;
import com.amatta.scheduler.amatta_back.app.common.enumeration.ChatRoomStatusCode;
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
@Table(name="tb_chat_room_master")
@Comment("채팅방 정보 테이블")
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChatRoomMaster extends AbstractAuditingEntity<String> implements Serializable {
    @Comment("채팅방 정보 고유 ID")
    @NotNull
    @Size(max=36)
    @Id
    @Column(name="id", length = 36,nullable = false,unique = true)
    private String id;

    @Comment("채팅방 이름")
    @NotNull
    @Size(max=500)
    @Column(name = "room_name",length = 500,nullable = false)
    private String roomName;

    @Comment("채팅방 상태코드")
    @NotNull
    @Size(max=100)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "chat_room_status_code",length = 100,nullable = false)
    private ChatRoomStatusCode chatRoomStatusCode;

    @Comment("마지막 채팅 메세지 고유 ID")
    @Size(max = 36)
    @Column(name ="last_chat_message_id" , length = 36)
    private String lastChatMessageId;

    @Comment("마지막 채팅 메세지 고유 ID")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_chat_message_id",insertable = false, updatable = false,foreignKey = @ForeignKey(name="fk_chatroommaster_chatmessagehist"))
    private ChatMessageHist chatMessageHist;
}
