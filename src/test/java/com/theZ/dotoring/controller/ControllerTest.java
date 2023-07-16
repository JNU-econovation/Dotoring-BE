package com.theZ.dotoring.controller;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.letter.controller.LetterFromMentiController;
import com.theZ.dotoring.app.letter.controller.LetterFromMentoController;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.repository.LetterRepository;
import com.theZ.dotoring.app.letter.service.LetterMentiService;
import com.theZ.dotoring.app.letter.service.LetterMentoService;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.service.RoomService;
import com.theZ.dotoring.enums.DeleteStatus;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired MockMvc mvc;

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MentoService mentoService;

    @Autowired
    private MentiService mentiServices;

    @Autowired
    private LetterFromMentoController letterFromMentoController;

    @Autowired
    private LetterFromMentiController letterFromMentiController;

    @Autowired
    private LetterMentoService letterMentoService;

    @Autowired
    private LetterMentiService letterMentiService;

    @Autowired
    private MentoRepository mentoRepository;

    @Autowired
    private MentiRepository mentiRepository;

    @Autowired
    private EntityManager em;

    /**
     * 웹 API 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET,POST 등에 대해 API 테스트 가능
     * */

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@Test
    @DisplayName("Hello Test")
    @Test
    @Transactional
    public void Hello_Test() throws Exception {

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기


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

        // Room 생성 -> 잘 생성
        Room room1 = roomService.findOrCreateRoom(mento, menti);

        System.out.println("room1.getWriter() : " + room1.getWriter());
        System.out.println("room1.getReceiver() : " + room1.getReceiver());

        // 쪽지 보내기
        // Letter 생성
        LetterByMemberRequestDTO letter1 = LetterByMemberRequestDTO.builder()
                .content("쪽지 내용이와요1")
                .build();
        // Letter 생성
        LetterByMemberRequestDTO letter2 = LetterByMemberRequestDTO.builder()
                .content("쪽지 내용이와요2")
                .build();
        // Letter 생성
        LetterByMemberRequestDTO letter3 = LetterByMemberRequestDTO.builder()
                .content("쪽지 내용이와요3")
                .build();
        // Letter 생성
        LetterByMemberRequestDTO letter4 = LetterByMemberRequestDTO.builder()
                .content("쪽지 내용이와요4")
                .build();

        // when -> 이게 문제, 저장이 최신 한 개 밖에 안돼, repo.save()도 여전히 1개
        // 영속성 문제는 그럼 아닌건가??
        // 잠시만 -> mento만 글을 작성하는게 문제인가?? -> 각각 나눠서 작성하니 1, 1
        // 즉 멘토가 썼던 것들은 멘토가 쓴 글 중 가장 나중 글만 저장
        // 멘티가 썼던 것은 멘티가 쓴 글 중 가장 나중 글만 저장
        // 각 Mento or MentiService단에 문제가 있다고 판단
        letterMentoService.sendLetterWhereIn(letter1, mento, room1);
        letterMentoService.sendLetterWhereIn(letter2, mento, room1);
        letterMentoService.sendLetterWhereIn(letter3, mento, room1);
        letterMentoService.sendLetterWhereIn(letter4, mento, room1);

        // then
        List<Letter> letters = letterRepository.findByRoom(room1).get();
        List<Letter> letterList1 = letterRepository.findAll();
        System.out.println("letters.size() : " + letters.size());
        System.out.println("letters1.size() : " + letterList1.size());

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("page", "0");
        info.add("size", "3");

        // then
        mvc.perform(get("/api/mento/letter/1/1")
                        .params(info))
                .andExpect(status().isOk())
                .andDo(print());

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산
        System.out.println("시간차이(m) : " + secDiffTime);

        // 페이징시에 시간차이(m) : 323ms
        // 페이징 안할시에 시간차이(m) :
    }
}
