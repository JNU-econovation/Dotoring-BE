package com.theZ.dotoring.app.letter.mapper;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.member.model.Member;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    // 매퍼 클래스에서 MentoMapper를 찾을 수 있도록 하는 코드
    LetterMapper INSTANCE = Mappers.getMapper(LetterMapper.class);

    // LetterRequestDto -> Letter 매핑
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(source = "member", target = "writer")
    @Mapping(source = "date", target = "createdAt")
    Letter toEntity(LetterByMemberRequestDTO letterRequestDTO, Member member, Date date);


    // List<Room> -> List<RoomResponseDto> 매핑, 이 때 반드시 Room -> RoomResponseDto 메서드가 먼저 있어야 한다.
    ArrayList<LetterByMemberResponseDTO> toDTOs(List<Letter> letter, @Context Member member);

    @Mapping(source = "letter.id", target = "letterId")
    @Mapping(source = "letter.createdAt", target = "createdAt")
    @Mapping(source = "letter.writer.id", target = "writer", qualifiedByName = "map")
    @Mapping(source = "letter.writer.nickname", target = "nickname")
    LetterByMemberResponseDTO toDTO(Letter letter, @Context Member member);

    // 메서드는 source를 인자로 받는다.
    @Named("map")
    default Boolean map (Long id, @Context Member member){
        return id.equals(member.getId());
    }
}