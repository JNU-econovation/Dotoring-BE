package com.theZ.dotoring.controller;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.letter.controller.LetterFromMentiController;
import com.theZ.dotoring.app.letter.controller.LetterFromMentoController;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
//@RunWith(SpringRunner.class)
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

    /**
     * 웹 API 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET,POST 등에 대해 API 테스트 가능
     * */

    //@Before
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@Test
    @DisplayName("Hello Test")
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

        // Mento 생성
        Mento mento1 = Mento.createMento("sonny1233", "sonny1233@", "sonny12@naver.com", "sonny", "안녕하세요, 현재 dotoring 프로젝트를 개발하고 있는 백엔드 개발자 sonny입니다.", "sun", List.of(certification1, certification2), "econo", 1L, Job.valueOf("정보통신"), Major.valueOf("산업공학과"));

        // Menti 생성
        Menti menti1 = Menti.createMenti("yardyard","sonny1233", "sonny12@naver.com", "sonny12", "안녕하세요, 저는 짱멋있는 케빈입니다.", "안녕하세요",  List.of(certification1, certification2), "eco", 1L, Major.valueOf("산업공학과"), Job.valueOf("정보통신"));

        Mento mento = mentoRepository.save(mento1);

        Menti menti = mentiRepository.save(menti1);

        // Room 생성
        Room room1 = roomService.findOrCreateRoom(mento, menti);

        // 쪽지 보내기
        // RequestDto -> Entity
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

        // when

        letterMentoService.sendLetterWhereIn(letter1, mento, room1);
        letterMentoService.sendLetterWhereIn(letter2, mento, room1);
        letterMentoService.sendLetterWhereIn(letter3, mento, room1);
        letterMentoService.sendLetterWhereIn(letter4, mento, room1);

        Pageable pageable = PageRequest.of(0, 2);

        // then
        List<Letter> letters = letterRepository.findByRoom(room1);
        System.out.println("letters.size() : " + letters.size());

        List<Letter> testLetters = letterRepository.findAll();
        System.out.println("letters.size() : " + testLetters.size());

        Slice<LetterByMemberResponseDTO> pageLists = letterMentoService.getLettersByOne(mento, room1, pageable);

        System.out.println("pageLists.getSize() : " + pageLists.getSize());

        for (LetterByMemberResponseDTO dto : pageLists) {
            System.out.println("answer : " + dto.getLetterId());
            System.out.println("answer : " + dto.getCreatedAt());
            System.out.println("answer : " + dto.isWriter());
        }

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
