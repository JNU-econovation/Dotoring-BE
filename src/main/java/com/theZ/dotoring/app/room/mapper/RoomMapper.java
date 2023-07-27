package com.theZ.dotoring.app.room.mapper;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoomMapper {
    // 매퍼 클래스에서 MentoMapper를 찾을 수 있도록 하는 코드
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    // List<Room> -> List<RoomResponseDto> 매핑, 이 때 반드시 Room -> RoomResponseDto 메서드가 먼저 있어야 한다.
    ArrayList<RoomResponseDTO> toDTOs(List<Room> room);

    @Mapping(source = "roomId", target = "roomPK")
    @Mapping(source = "receiver.nickname", target = "nickname")
    @Mapping(source = "receiver.id", target = "memberPK")
    @Mapping(source = "letterList", target = "lastLetter", qualifiedByName = "letterListToStr")
    @Mapping(source = "receiver.job", target = "major")
    @Mapping(source = "lastSendTime", target = "updateAt")
    RoomResponseDTO toDTO(Room room);

    @Named("letterListToStr")
    static String letterListToStr(List<Letter> letterList) {
        if (letterList.isEmpty()) {
            return null;
        }
        return letterList.get(letterList.size() - 1).getContent();
    }

}