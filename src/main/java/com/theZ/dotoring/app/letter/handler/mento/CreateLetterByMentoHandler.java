package com.theZ.dotoring.app.letter.handler.mento;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.dto.LetterFromOutResponseDTO;
import com.theZ.dotoring.app.letter.mapper.LetterMapper;
import com.theZ.dotoring.app.letter.service.LetterMentoService;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateLetterByMentoHandler {

    final private LetterMentoService letterManagementService;

    final private MentoService mentoService;

    final private MentiService mentiService;

    final private RoomService roomService;

    @Transactional
    public LetterFromOutResponseDTO execute(LetterByMemberRequestDTO letterRequestDTO, Long mentoId, Long mentiId){

        Mento user = mentoService.findMento(mentoId);

        Menti menti = mentiService.findMenti(mentiId);

        Room room = roomService.findOrCreateRoom(user, menti);

        Letter letter = letterManagementService.sendLetterWhereOut(letterRequestDTO, user, room);

        LetterMapper.INSTANCE.toDTO(letter, user);

        LetterFromOutResponseDTO letterFromOutResponseDTO = LetterFromOutResponseDTO.builder()
                .roomPK(room.getId())
                .build();

        return letterFromOutResponseDTO;
    }
}
