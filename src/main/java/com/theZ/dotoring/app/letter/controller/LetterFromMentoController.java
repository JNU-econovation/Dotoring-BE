package com.theZ.dotoring.app.letter.controller;

import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.handler.mento.CreateMentoLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.mento.CreateLetterByMentoHandler;
import com.theZ.dotoring.app.letter.handler.mento.GetMentoLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.mento.GetRoomByMentoHandler;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LetterFromMentoController {

    private final CreateLetterByMentoHandler createLetterMember2MemberHandler;

    private final CreateMentoLetterByRoomHandler createLetterFromRoomHandler;

    private final GetRoomByMentoHandler getRoomsFromMemberHandler;

    private final GetMentoLetterByRoomHandler getLettersFromMemberHandler;

    @PostMapping("api/mento/letter/{mentoId}/{mentiId}")
    public void sendLetterWhereOut(@RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("mentoId") Long mentoId, @PathVariable("mentiId") Long mentiId) {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterMember2MemberHandler.execute(letterRequestDTO, mentoId, mentiId);
    }

    @PostMapping("api/mento/letter/{mentoId}/{roomPK}")
    public void sendLetterWhereIn(@RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("roomPK") Long mentoId, @PathVariable("roomPK") Long roomPK) {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterFromRoomHandler.execute(letterRequestDTO, mentoId, roomPK);
    }

    @GetMapping("api/mento/letter/{mentoId}/{mentiId}")
    public List<RoomResponseDTO> getRooms(Long mentoId, Long mentiId) {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        return getRoomsFromMemberHandler.execute(mentoId, mentiId);
    }

    @GetMapping("api/mento/letter/{roomPK}/{mentoId}")
    public Slice<LetterByMemberResponseDTO> getLetters(@RequestParam int page, @RequestParam int size,
                                                       @PathVariable Long roomPK, @PathVariable Long mentoId) {
        return getLettersFromMemberHandler.execute(page, size, roomPK, mentoId);
    }
}