package com.theZ.dotoring.room.repository;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.repository.RoomQueryRepositoryImpl;
import com.theZ.dotoring.app.room.repository.RoomRepository;
import com.theZ.dotoring.enums.DeleteStatus;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomQueryRepositoryImpl roomQueryRepository;

    @Autowired
    private MentoRepository mentoRepository;

    @Autowired
    private MentiRepository mentiRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    @BeforeEach
    public void set_up() {
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

        certificationRepository.saveAll(List.of(certification1, certification2));

        // Mento 생성 -> 잘 생성
        Mento mento1 = Mento.createMento("sonny1233", "sonny1233@", "sonny12@naver.com", "sonny", "안녕하세요, 현재 dotoring 프로젝트를 개발하고 있는 백엔드 개발자 sonny입니다.", "sun", List.of(certification1, certification2), "econo", 1L, Job.valueOf("정보통신"), Major.valueOf("산업공학과"));

        // Menti 생성 -> 잘 생성
        Menti menti1 = Menti.createMenti("yardyard","sonny1233", "sonny12@naver.com", "sonny12", "안녕하세요, 저는 짱멋있는 케빈입니다.", "안녕하세요",  List.of(certification1, certification2), "eco", 1L, Major.valueOf("산업공학과"), Job.valueOf("정보통신"));

        Mento mento = mentoRepository.save(mento1);

        Menti menti = mentiRepository.save(menti1);
        
        // Room 생성


        em.flush();
        em.clear();
    }

    @Test
    public void test1(){
        // given
        Optional<Mento> mento = mentoRepository.findById(1L);
        Optional<Menti> menti = mentiRepository.findById(2L);

        // when
        List<Room> rooms = roomRepository.findAllByWriterOrReceiver(mento.get(), menti.get()).get();

        // then
        Assertions.assertThat(rooms.size()).isEqualTo(1);
    }
}
