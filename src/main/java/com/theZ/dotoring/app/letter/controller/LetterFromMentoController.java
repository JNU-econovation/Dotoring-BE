package com.theZ.dotoring.app.letter.controller;

import com.theZ.dotoring.app.auth.MemberDetails;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.dto.LetterFromOutResponseDTO;
import com.theZ.dotoring.app.letter.handler.mento.CreateLetterByMentoHandler;
import com.theZ.dotoring.app.letter.handler.mento.CreateMentoLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.mento.GetMentoLetterByRoomHandler;
import com.theZ.dotoring.app.letter.handler.mento.GetRoomByMentoHandler;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.exception.NotFoundLetterException;
import com.theZ.dotoring.exception.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class LetterFromMentoController {

    private final CreateLetterByMentoHandler createLetterMember2MemberHandler;

    private final CreateMentoLetterByRoomHandler createLetterFromRoomHandler;

    private final GetRoomByMentoHandler getRoomsFromMemberHandler;

    private final GetMentoLetterByRoomHandler getLettersFromMemberHandler;

    @PostMapping("api/mento/letter/out/{mentiId}")
    public ApiResponse<ApiResponse.CustomBody<LetterFromOutResponseDTO>> sendLetterWhereOut(@Valid @RequestBody LetterByMemberRequestDTO letterRequestDTO, @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("mentiId") Long mentiId) {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        LetterFromOutResponseDTO letterFromOutResponseDTO = createLetterMember2MemberHandler.execute(letterRequestDTO, memberDetails.getId(), mentiId);
        return ApiResponseGenerator.success(letterFromOutResponseDTO, HttpStatus.OK);
    }

    @PostMapping("api/mento/letter/in/{roomPK}")
    public void sendLetterWhereIn(@Valid @RequestBody LetterByMemberRequestDTO letterRequestDTO, @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("roomPK") Long roomPK) {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        createLetterFromRoomHandler.execute(letterRequestDTO, memberDetails.getId(), roomPK);
    }

    @GetMapping("api/mento/room")
    public ApiResponse<ApiResponse.CustomBody<List<RoomResponseDTO>>> getRooms(@AuthenticationPrincipal MemberDetails memberDetails) throws NotFoundRoomException {
        // mentoId : 멘토인 내 아이디 -> 시큐리티 도입과 함께 추후 삭제 되어야 함.
        return ApiResponseGenerator.success(getRoomsFromMemberHandler.execute(memberDetails.getId()), HttpStatus.OK);
    }

    @GetMapping("api/mento/letter/{roomPK}")
    public ApiResponse<ApiResponse.CustomBody<Slice<LetterByMemberResponseDTO>>> getLetters(@RequestParam int page, @RequestParam int size,
                                                       @PathVariable Long roomPK, @AuthenticationPrincipal MemberDetails memberDetails) throws NotFoundLetterException {
        return ApiResponseGenerator.success(getLettersFromMemberHandler.execute(page, size, roomPK, memberDetails.getId()), HttpStatus.OK);
    }
}