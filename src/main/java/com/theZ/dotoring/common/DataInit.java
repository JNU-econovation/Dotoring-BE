package com.theZ.dotoring.common;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.repository.LetterRepository;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.repository.RoomRepository;
import com.theZ.dotoring.enums.DeleteStatus;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final CertificationRepository certificationRepository;

    private final EntityManager em;

    private final MentiRepository mentiRepository;

    private final MentoRepository mentoRepository;

    private final RoomRepository roomRepository;

    private final LetterRepository letterRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {

        List<Certification> certifications = generateCertifications(); // 객체상태

        certificationRepository.saveAll(List.of(certifications.get(0), certifications.get(1)));

        em.flush();

        List<Menti> mentis = generateMenties(certifications.get(0), certifications.get(1));

        Mento mento = generateMentos(certifications.get(0), certifications.get(1));
        certificationRepository.saveAll(List.of(certifications.get(0), certifications.get(1)));
        mentiRepository.saveAll(mentis);
        mentoRepository.save(mento);


        /**
         *  1번
         */

        Room room1 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(1))
                .build();

        room1.updateLastSendTime();

        Room roomSP1 = roomRepository.save(room1);

        List<Letter> letters = generateLetters1(roomSP1);

        letterRepository.saveAll(letters);

        /**
         *  2번
         */

        Room room2 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(2))
                .build();

        room2.updateLastSendTime();

        Room roomSP2 = roomRepository.save(room2);

        List<Letter> letters2 = generateLetters2(roomSP2);

        letterRepository.saveAll(letters2);

        /**
         *  3번
         */

        Room room3 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(3))
                .build();

        room3.updateLastSendTime();

        Room roomSP3 = roomRepository.save(room3);

        List<Letter> letters3 = generateLetters3(roomSP3);

        letterRepository.saveAll(letters3);

        /**
         *  4번
         */

        Room room4 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(4))
                .build();

        room4.updateLastSendTime();

        Room roomSP4 = roomRepository.save(room4);

        List<Letter> letters4 = generateLetters4(roomSP4);

        letterRepository.saveAll(letters4);

        /**
         *  5번
         */

        Room room5 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(6))
                .build();

        room5.updateLastSendTime();

        Room roomSP5 = roomRepository.save(room5);

        List<Letter> letters5 = generateLetters5(roomSP5);

        letterRepository.saveAll(letters5);

        /**
         *  6번
         */

        Room room6 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(7))
                .build();

        room6.updateLastSendTime();

        Room roomSP6 = roomRepository.save(room6);

        List<Letter> letters6 = generateLetters6(roomSP6);

        letterRepository.saveAll(letters6);

        /**
         *  7번
         */

        Room room7 = Room.builder()
                .writer(mento)
                .receiver(mentis.get(7))
                .build();

        room7.updateLastSendTime();

        Room roomSP7 = roomRepository.save(room7);

        List<Letter> letters7 = generateLetters7(roomSP7);

        letterRepository.saveAll(letters7);
    }


    private Mento generateMentos(Certification certification1, Certification certification2) {

        Mento mento = Mento.createMento("qwer1111", bCryptPasswordEncoder.encode("qwer1111"), "qwer1111@naver.com", "qwer1111", "안녕하세요, 현재 배달의 민족에서 CTO를 맡고있는 백엔드 개발자 김영한입니다.", "sun", List.of(certification1, certification2), "배달의 민족", 1L, Job.valueOf("정보통신"), Major.valueOf("컴퓨터공학전공"));

        return mento;
    }

    private List<Certification> generateCertifications() {

        List<Certification> certifications = new ArrayList<>();

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

        certifications.add(certification1);
        certifications.add(certification2);

        return certifications;
    }

    private List<Menti> generateMenties(Certification certification1, Certification certification2) {
        List<Menti> mentis = new ArrayList<>();
		/*
		소공 & 전자
		 */
        mentis.add(Menti.createMenti("qwer5678", "yard5678", "qwer5678@naver.com", "qwer5678", "안녕하세요, 저는 짱멋있는 경준입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 1L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("sonny5678", "sonny5678", "sonny5678@naver.com", "sonny567", "안녕하세요, 저는 짱멋있는 도연입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("전기컴퓨터공학부"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("holy5678", "holy5678", "holy5678@naver.com", "holy5678", "안녕하세요, 저는 짱멋있는 지민입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("yard5678", "yard5678", "yard5678@naver.com", "yard5678", "안녕하세요, 저는 짱멋있는 가연입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 4L, Major.valueOf("전기컴퓨터공학부"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("dorae5678", "dorae5678", "dorae5678@naver.com", "dora5678", "안녕하세요, 저는 짱멋있는 건형입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("ramen5678", "ramen5678", "ramen5678@naver.com", "rame678", "안녕하세요, 저는 짱멋있는 수현입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("sunn5678", "suns5678", "sun5678@naver.com", "sun5678", "안녕하세요, 저는 짱멋있는 수민입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("intel5678", "int5678", "intel5678@naver.com", "inte5678", "안녕하세요, 저는 짱멋있는 종준입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 1L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("haha5678", "haha5678", "haha5678@naver.com", "haha5678", "안녕하세요, 저는 짱멋있는 하하입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("전기컴퓨터공학부"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("hoho5678", "hoho5678", "hoho5678@naver.com", "hoho5678", "안녕하세요, 저는 짱멋있는 호호입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 4L, Major.valueOf("전기컴퓨터공학부"), Job.valueOf("정보통신")));



        mentis.add(Menti.createMenti("yardyard", "yardyard", "yardyard@naver.com", "yardyard", "안녕하세요, 저는 짱멋있는 케빈입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 1L, Major.valueOf("가정교육과"), Job.valueOf("건설")));
        mentis.add(Menti.createMenti("sonny1234", "sonny1234", "sonny1234@naver.com", "sonn1234", "안녕하세요, 저는 짱멋있는 쏘니입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("간호학과"), Job.valueOf("경영")));
        mentis.add(Menti.createMenti("holy1234", "holy1234", "holy1234@naver.com", "holy1234", "안녕하세요, 저는 짱멋있는 홀리입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("건축_도시설계전공"), Job.valueOf("사무")));
        mentis.add(Menti.createMenti("yard7899", "yard7899", "yard7899@naver.com", "yard7899", "안녕하세요, 저는 짱멋있는 야드입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 4L, Major.valueOf("건축공학전공"), Job.valueOf("금융")));
        mentis.add(Menti.createMenti("doraemon", "doraemon", "doraemon@naver.com", "doraemon", "안녕하세요, 저는 짱멋있는 도라에몽입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("건축디자인학과"), Job.valueOf("보험")));
        mentis.add(Menti.createMenti("ramen3333", "ramen3333", "ramen3333@naver.com", "rame3333", "안녕하세요, 저는 짱멋있는 라멘입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("건축학부"), Job.valueOf("교육")));
        mentis.add(Menti.createMenti("sunshine", "sunshine", "sunshine@naver.com", "sunshine", "안녕하세요, 저는 짱멋있는 시현입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("경영학부"), Job.valueOf("법률")));
        mentis.add(Menti.createMenti("intellij", "intellij", "intellij@naver.com", "intellij", "안녕하세요, 저는 짱멋있는 현지입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 1L, Major.valueOf("경제학부"), Job.valueOf("사회복지")));
        mentis.add(Menti.createMenti("haha1234", "haha1234", "haha1234@naver.com", "haha1234", "안녕하세요, 저는 짱멋있는 하하입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("고분자융합소재공학부"), Job.valueOf("경찰")));
        mentis.add(Menti.createMenti("hoho1234", "hoho1234", "hoho1234@naver.com", "hoho1234", "안녕하세요, 저는 짱멋있는 호호입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 4L, Major.valueOf("산업공학과"), Job.valueOf("소방_군인")));


        mentis.add(Menti.createMenti("dfcassard", "dfcs1234", "dfcs1234@naver.com", "dfcs1234", "안녕하세요, 저는 멋있는 디에프씨에스입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("avdv1233", "avdv1233", "avdv1233@naver.com", "avdv1233", "안녕하세요, 저는 야무진 에이디입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("산업공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("jkjk1234", "jkjk1234", "jkjk1234@naver.com", "jkjk1234", "안녕하세요, 저는 귀여운 제이케이입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("dore1234", "dore1234", "dore1234@naver.com", "dore1234", "안녕하세요, 저는 미친 도레입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 4L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("aaaa1234", "aaaa1234", "aaaa1234@naver.com", "aaaa1234", "안녕하세요, 저는 짱멋있는 아멘입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("산업공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("ramen1234", "ramen1234", "ramen1234@naver.com", "rame1234", "안녕하세요, 저는 짱멋있는 라멘입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("junsu111", "junsu111", "junsu111@naver.com", "juns111", "안녕하세요, 저는 짱멋있는 준수입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 3L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("minji123", "minji123", "minji123@naver.com", "minj123", "안녕하세요, 저는 짱멋있는 민지입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 1L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("dongwan14", "dongwan14", "dongwan14@naver.com", "dongwa14", "안녕하세요, 저는 짱멋있는 동완입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        mentis.add(Menti.createMenti("hongjun14", "hongjun14", "hongjun14@naver.com", "hongju14", "안녕하세요, 저는 짱멋있는 홍준입니다.", "basicProfile_47838475947393908393.png", List.of(certification1, certification2), "전남대학교", 2L, Major.valueOf("소프트웨어공학과"), Job.valueOf("정보통신")));
        return mentis;
    }

    private List<Letter> generateLetters1(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("안녕하세요!! 반갑습니다. 저는 김영한이라고 해요 ㅎㅎ").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("안녕하세요~ 팬입니다").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("혹시 어떤 요일이 괜찮으실까요??").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("저는 수요일 빼고 다 괜찮습니다!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 알겠습니다.").room(room).writer(room.getReceiver()).build());
        return letters;
    }

    private List<Letter> generateLetters2(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("안녕하세요~~~!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("안녕하세요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("김영한 멘토님 반갑습니다!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 반갑습니다~!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("어떤 고민 때문에 연락하셨나요?").room(room).writer(room.getWriter()).build());
        return letters;
    }

    private List<Letter> generateLetters3(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("김영한 멘토님 안녕하세요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("멘토링 받고 싶습니다!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("아 안녕하세요!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("안뇽하세요~!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("언제가 시간이 괜찮으세요?").room(room).writer(room.getWriter()).build());
        return letters;
    }

    private List<Letter> generateLetters4(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("안녕하세요! 반갑습니다~!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("헉 진짜 반가워요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("진짜 김영한님이신가요??").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 맞습니다 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("반갑습니당").room(room).writer(room.getWriter()).build());
        return letters;
    }

    private List<Letter> generateLetters5(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("스프링 부트를 마스터 하고 싶습니다....").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 잘 찾아오셨습니다.").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("안녕하세요 전 이승건이에요.").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("안녕하세요 저는 김영한입니다.").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("멘토링 신청해주세요!").room(room).writer(room.getWriter()).build());
        return letters;
    }

    private List<Letter> generateLetters6(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("안녕하세요!! 저는 백엔드 개발자 김영한입니다!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("넵 안녕하세여~!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("어떤 멘토링을 받기를 원하나요??").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("저는 스프링에 대해서 멘토링을 받고 싶어요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 뭐든 가능합니다!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("감사합니다~!!!!").room(room).writer(room.getReceiver()).build());
        return letters;
    }

    private List<Letter> generateLetters7(Room room) {
        List<Letter> letters = new ArrayList<>();
        // 멘토 1명이 멘티 5명과 쪽지를 주고받는 것을 만들어야한다.
        // Room.receiver -> 멘티, Room.writer -> 멘토
        letters.add(Letter.builder().content("Hi I'm YH Kim").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("안녕하세요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("어떤 멘토링을 받기를 원하나요??").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("저는 쿼리 튜닝에 대해서 중점적으로 멘토링을 받고 싶어요").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("그리고, DB 설계에 대해서도 멘토링을 받고 싶어요").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("마지막으로, 자바에 대해서 공부하고 싶어요!!").room(room).writer(room.getReceiver()).build());
        letters.add(Letter.builder().content("넵 뭐든 가능합니다!!").room(room).writer(room.getWriter()).build());
        letters.add(Letter.builder().content("감사합니다~").room(room).writer(room.getReceiver()).build());
        return letters;
    }
}
