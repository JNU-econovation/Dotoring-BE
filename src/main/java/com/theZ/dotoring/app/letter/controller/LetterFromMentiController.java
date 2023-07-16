package com.theZ.dotoring.app.letter.controller;

import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.handler.menti.CreateLetterByMentiHandler;
import com.theZ.dotoring.app.letter.handler.menti.CreateMentiLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.menti.GetMentiLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.menti.GetRoomByMentiHandler;
import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class LetterFromMentiController {

    private final CreateLetterByMentiHandler createLetterByMentiHandler;

    private final CreateMentiLetterByRoomHandler createLetterByRoomHandler;

    private final GetMentiLetterByRoomHandler getLetterByRoomHandler;

    private final GetRoomByMentiHandler getRoomByMentiHandler;


    @PostMapping("api/menti/letter/out/{mentoId}/{mentiId}")
    public ApiResponse<ApiResponse.CustomBody<Void>> sendLetterWhereOut(@Valid @RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("mentoId") Long mentoId, @PathVariable("mentiId") Long mentiId) {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterByMentiHandler.execute(letterRequestDTO, mentoId, mentiId);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("api/menti/letter/in/{mentoId}/{roomPK}")
    public ApiResponse<ApiResponse.CustomBody<Void>> sendLetterWhereIn(@Valid @RequestBody LetterByMemberRequestDTO letterRequestDTO, @PathVariable("mentoId") Long mentoId, @PathVariable("roomPK") Long roomPK) {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterByRoomHandler.execute(letterRequestDTO, mentoId, roomPK);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("api/menti/room/{mentoId}/{mentiId}")
    public ApiResponse<ApiResponse.CustomBody<List<RoomResponseDTO>>> getRooms(@PathVariable("mentoId") Long mentoId, @PathVariable("mentiId") Long mentiId) throws Exception {
        // mentiId : 멘티인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        return ApiResponseGenerator.success(getRoomByMentiHandler.execute(mentoId, mentiId), HttpStatus.OK);
    }

    @GetMapping("api/menti/letter/{roomPK}/{mentiId}")
    public ApiResponse<ApiResponse.CustomBody<Slice<LetterByMemberResponseDTO>>> getLetters(@RequestParam int page, @RequestParam int size,
                                                       @PathVariable("roomPK") Long roomPK, @PathVariable("mentiId") Long mentiId) throws Exception {
        return ApiResponseGenerator.success(getLetterByRoomHandler.execute(page, size, roomPK, mentiId), HttpStatus.OK);
    }
}