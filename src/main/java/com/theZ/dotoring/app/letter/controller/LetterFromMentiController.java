package com.theZ.dotoring.app.letter.controller;

import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.handler.menti.CreateLetterByMentiHandler;
import com.theZ.dotoring.app.letter.handler.menti.CreateMentiLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.menti.GetMentiLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.menti.GetRoomByMentiHandler;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LetterFromMentiController {

    private final CreateLetterByMentiHandler createLetterByMentiHandler;

    private final CreateMentiLetterByRoomHandler createLetterByRoomHandler;

    private final GetMentiLetterByRoomHandler getLetterByRoomHandler;

    private final GetRoomByMentiHandler getRoomByMentiHandler;


    @PostMapping("api/letter/{mentoId}/{mentiId}")
    public void sendLetterWhereOut(@RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("mentoId") Long mentoId, @PathVariable("mentiId") Long mentiId) {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterByMentiHandler.execute(letterRequestDTO, mentoId, mentiId);
    }

    @PostMapping("api/letter/{mentoId}/{roomPK}")
    public void sendLetterWhereIn(@RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("roomPK") Long mentoId, @PathVariable("roomPK") Long roomPK) {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterByRoomHandler.execute(letterRequestDTO, mentoId, roomPK);
    }

    @GetMapping("api/letter/{mentoId}/{mentiId}")
    public List<RoomResponseDTO> getRooms(Long mentoId, Long mentiId) {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        return getRoomByMentiHandler.execute(mentoId, mentiId);
    }

    @GetMapping("api/letter/{roomPK}/{mentiId}")
    public Slice<LetterByMemberResponseDTO> getLetters(@RequestParam int page, @RequestParam int size,
                                                       @PathVariable Long roomPK, @PathVariable Long mentiId) {
        return getLetterByRoomHandler.execute(page, size, roomPK, mentiId);
    }


}