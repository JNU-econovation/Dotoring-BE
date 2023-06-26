package com.theZ.dotoring.mento.repository;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.enums.DeleteStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

@DataJpaTest
public class MentoRepositoryTest {

    @Autowired
    private MentoRepository mentoRepository;

    @Autowired
    private CertificationRepository certificationRepository;
    
    @DisplayName("증명서를 먼저 저장한 후에 멘토를 저장 - CascadeType.PERSIST 테스트")
    @Test
    void saveMember(){

        // given
        String originalFileName1 = "sonnyTest.img";
        String uuid1 = UUID.randomUUID().toString();
        String storeFileName1 = uuid1 + "." + "img";

        Certification certification1 = Certification.builder()
                .originalFileName(originalFileName1)
                .saveFileName(storeFileName1)
                .delete_yn(DeleteStatus.NO)
                .build();

        String originalFileName2 = "bigsun.pdf";
        String uuid2 = UUID.randomUUID().toString();
        String storeFileName2 = uuid2 + "." + "pdf";

        Certification certification2 = Certification.builder()
                .originalFileName(originalFileName2)
                .saveFileName(storeFileName2)
                .delete_yn(DeleteStatus.NO)
                .build();
        certificationRepository.saveAll(List.of(certification1,certification2));

        // when

        Mento mento = Mento.createMento("sonny1233", "sonny1233@", "sonny12@naver.com", "sonny", "안녕하세요, 현재 dotoring 프로젝트를 개발하고 있는 백엔드 개발자 sonny입니다.", List.of(certification1, certification2), "econo", 1L, "대학생");

        Mento savedMento = mentoRepository.save(mento);
        // then
        Assertions.assertThat(savedMento.getCertifications().size()).isEqualTo(2);
        Assertions.assertThat(certification1.getMember().getEmail()).isEqualTo("sonny12@naver.com");
    }
}
