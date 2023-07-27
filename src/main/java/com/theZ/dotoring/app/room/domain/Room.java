package com.theZ.dotoring.app.room.domain;


import com.theZ.dotoring.app.commonModel.CommonEntity;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.model.Mento;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends CommonEntity {

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Letter> letterList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "writer_id") // writer_id는 Writer(작성자)의 식별자 컬럼 이름입니다.
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "receiver_id") // receiver_id는 Receiver(수신자)의 식별자 컬럼 이름입니다.
    private Member receiver;

    private LocalDateTime lastSendTime;

    public void updateLastSendTime(){
        this.lastSendTime = LocalDateTime.now();
    }
}
