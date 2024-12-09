package com.amatta.scheduler.amatta_back.app.socket.domain;

import com.amatta.scheduler.amatta_back.app.common.domain.AbstractAuditingEntity;
import com.amatta.scheduler.amatta_back.app.common.domain.AttachDocMaster;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
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
@Table(name="tb_chat_message_hist")
@Comment("채팅 메세지 이력 테이블")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChatMessageHist extends AbstractAuditingEntity<String> implements Serializable {

    @Comment("채팅 메세지 고유 ID")
    @Size(max=36)
    @NotNull
    @Id
    @Column(name = "id",nullable = false ,unique = true)
    private String id;

    @Comment("채팅방 고유 ID")
    @Size(max=36)
    @NotNull
    @Column(name = "chat_room_id",nullable = false)
    private String chatRoomId;

    @Comment("채팅방 정보 테이블")
    @ManyToOne @JoinColumn(name = "chat_room_id",insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_chatmessagehist_chatroommaster"))
    private ChatRoomMaster chatRoomMaster;

    @Comment("회원 고유 ID")
    @Size(max=36)
    @NotNull
    @Column(name = "user_master_id",nullable = false)
    private String userMasterId;
    /**
     * TODO: attach_doc_master 테이블 생성 후 조인 설정 잡기
     */
    @Comment("첨부 문서 정보 고유 ID")
    @Size(max=36)
    @Column(name = "attach_doc_id")
    private String attachDocId;

    @Comment("첨부 문서 정보 테이블")
    @OneToOne @JoinColumn(name = "attach_doc_id",insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_chatmessagehist_attachdocmaster"))
    private AttachDocMaster attachDocMaster;

    @Comment("채팅 메세지 전송 내용")
    @Size(max=5000)
    @Column(name = "send_message_text",length = 5000)
    private String sendMessageText;
}
