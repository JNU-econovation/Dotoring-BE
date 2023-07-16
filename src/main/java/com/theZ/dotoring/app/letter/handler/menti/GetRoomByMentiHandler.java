package com.theZ.dotoring.app.letter.handler.menti;

import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import com.theZ.dotoring.app.room.mapper.RoomMapper;
import com.theZ.dotoring.app.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRoomByMentiHandler {

    final private RoomService roomService;

    @Transactional
    public List<RoomResponseDTO> execute(Long mentoId, Long mentiId) throws Exception {
        List<Room> room = roomService.findAllByUserId(mentoId, mentiId);

        List<RoomResponseDTO> responseDTOList = RoomMapper.INSTANCE.toDTOs(room);

        return responseDTOList;
    }
}
