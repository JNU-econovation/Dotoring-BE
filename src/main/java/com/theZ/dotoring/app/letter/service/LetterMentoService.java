package com.theZ.dotoring.app.letter.service;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.mapper.LetterMapper;
import com.theZ.dotoring.app.letter.repository.LetterRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterMentoService {

    private final EntityManager em;

    private final LetterRepository letterRepository;

    private final RoomRepository roomRepository;

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
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public Letter sendLetterWhereIn(LetterByMemberRequestDTO letterRequestDTO, Mento user, Room room) {

        Letter letter = LetterMapper.INSTANCE.toEntity(letterRequestDTO, user, new Date());

        // 양방향 연관 관계
        letter.addLetter(room);

        Letter savedLetter = letterRepository.save(letter);

        return savedLetter;
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
