package com.theZ.dotoring.app.letter.domain;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.room.domain.Room;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {

    @Id
    @Column(name = "letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_id") // writer_id는 Writer(작성자)의 식별자 컬럼 이름입니다.
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // letterList에 추가하기
    public void addLetter(Room room){
        if (this.room != null) {
            this.room.getLetterList().remove(this);
        }
        this.room = room;
        if (room != null) {
            room.getLetterList().add(this);
        }
    }
}