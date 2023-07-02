package com.theZ.dotoring.app.certification.model;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.DeleteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private String originalFileName;

    private String saveFileName;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    private DeleteStatus delete_yn;

    private LocalDateTime deletedAt;


    public void mappingMember(Member member) {
        this.member = member;
    }

    @Builder
    public Certification(String originalFileName, String saveFileName, DeleteStatus delete_yn){
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.delete_yn = delete_yn;
    }

    public static List<Certification> createCertifications(List<String> originalFileNames, List<String> saveFileNames){
        return IntStream.range(0, originalFileNames.size())
                .mapToObj(i -> Certification.builder()
                        .originalFileName(originalFileNames.get(i))
                        .saveFileName(saveFileNames.get(i))
                        .delete_yn(DeleteStatus.NO)
                        .build())
                .collect(Collectors.toList());
    }
}
