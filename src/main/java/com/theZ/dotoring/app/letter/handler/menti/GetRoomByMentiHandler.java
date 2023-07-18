package com.theZ.dotoring.app.letter.handler.menti;

import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import com.theZ.dotoring.app.room.mapper.RoomMapper;
import com.theZ.dotoring.app.room.service.RoomService;
import com.theZ.dotoring.exception.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRoomByMentiHandler {

    final private RoomService roomService;

    @Transactional
    public List<RoomResponseDTO> execute(Long mentiId) throws NotFoundRoomException {
        List<Room> room = roomService.findAllByMentiId(mentiId);

        List<RoomResponseDTO> responseDTOList = RoomMapper.INSTANCE.toDTOs(room);

        return responseDTOList;
    }
}
