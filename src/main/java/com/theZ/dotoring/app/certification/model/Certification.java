package com.theZ.dotoring.app.certification.model;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_FK")
    private Member member;

    private String originalName;

    private String saveName;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    private DeleteStatus delete_yn;

    private LocalDateTime deletedAt;

    private Integer size;

    public void mappingMember(Member member) {
        this.member = member;
    }
}
