package com.theZ.dotoring.app.room.mapper;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.dto.RoomResponseDTO;
import com.theZ.dotoring.enums.Job;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-07T12:53:32+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class RoomMapperImpl implements RoomMapper {

    @Override
    public ArrayList<RoomResponseDTO> toDTOs(List<Room> room) {
        if ( room == null ) {
            return null;
        }

        ArrayList<RoomResponseDTO> arrayList = new ArrayList<RoomResponseDTO>();
        for ( Room room1 : room ) {
            arrayList.add( toDTO( room1 ) );
        }

        return arrayList;
    }

    @Override
    public RoomResponseDTO toDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO.RoomResponseDTOBuilder roomResponseDTO = RoomResponseDTO.builder();

        roomResponseDTO.roomPK( room.getId() );
        roomResponseDTO.nickname( roomReceiverNickname( room ) );
        roomResponseDTO.memberPK( roomReceiverId( room ) );
        roomResponseDTO.lastLetter( RoomMapper.letterListToStr( room.getLetterList() ) );
        Job job = roomReceiverJob( room );
        if ( job != null ) {
            roomResponseDTO.major( job.name() );
        }
        roomResponseDTO.updateAt( room.getLastSendTime() );

        return roomResponseDTO.build();
    }

    private String roomReceiverNickname(Room room) {
        if ( room == null ) {
            return null;
        }
        Member receiver = room.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        String nickname = receiver.getNickname();
        if ( nickname == null ) {
            return null;
        }
        return nickname;
    }

    private Long roomReceiverId(Room room) {
        if ( room == null ) {
            return null;
        }
        Member receiver = room.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        Long id = receiver.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Job roomReceiverJob(Room room) {
        if ( room == null ) {
            return null;
        }
        Member receiver = room.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        Job job = receiver.getJob();
        if ( job == null ) {
            return null;
        }
        return job;
    }
}
