package com.theZ.dotoring.app.letter.service;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.mapper.LetterMapper;
import com.theZ.dotoring.app.letter.repository.LetterRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.room.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterMentoService {

    // private final RoomService roomService;

    private final LetterRepository letterRepository;

    // 밖에서 쪽지 보내기
    @Transactional
    public Letter sendLetterWhereOut(LetterByMemberRequestDTO letterRequestDTO, Mento user, Room room) {
        Letter letter = LetterMapper.INSTANCE.toEntity(letterRequestDTO, user, new Date());

        // 양방향 연관 관계
        letter.addLetter(room);

        letterRepository.save(letter);

        return letter;
    }

    // 안에서 쪽지 보내기

    public Letter sendLetterWhereIn(LetterByMemberRequestDTO letterRequestDTO, Mento user, Room room) {
        Letter letter = LetterMapper.INSTANCE.toEntity(letterRequestDTO, user, new Date());
        // 양방향 연관 관계
        letter.addLetter(room);

        letterRepository.save(letter);

        return letter;
    }

/*    @Transactional(readOnly = true)
    public List<Room> getLettersByGroup(Long userId) {

        //Move To RoomService
        List<Room> roomList = roomService.findAllByUserId(userId);

        return roomList;
    }*/

    // 해당 Room에 해당하는 메시지들 반환
    @Transactional(readOnly = true)
    public Slice<LetterByMemberResponseDTO> getLettersByOne(Mento user, Room room, Pageable pageable) {
        Slice<Letter> letters = letterRepository.findByRoom(room, pageable);

        List<LetterByMemberResponseDTO> letterResponseDTOS = LetterMapper.INSTANCE.toDTOs(letters.getContent(), user);

        return new PageImpl<>(letterResponseDTOS, pageable, letterResponseDTOS.size());
    }
}
