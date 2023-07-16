package com.theZ.dotoring.app.room.service;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final MentoService mentoService;

    private final MentiService mentiService;

    @Transactional
    public Room findOrCreateRoom(Member writer, Member receiver) {
        
        // 만들어진 방이 하나라도 있는지 검증 
        // todo 추후 Mento, Menti 분리하기
        Optional<Room> room1 = roomRepository.findByWriterAndReceiver(writer, receiver);
        Optional<Room> room2 = roomRepository.findByWriterAndReceiver(receiver, writer);

        if (room1.isPresent()) {
            Room roomEntity = room1.get();
            return roomEntity;
        }

        if (room2.isPresent()) {
            Room roomEntity = room2.get();
            return roomEntity;
        }

        Room newRoom = makeRoomObject(writer, receiver);

        return saveRoom(newRoom);
    }

    @Transactional(readOnly = true)
    public Room findByRoomId(Long roomId) {
        return roomRepository.findById(roomId).isPresent() ?
                roomRepository.findById(roomId).get(): null;
    }

    @Transactional(readOnly = true)
    public List<Room> findAllByUserId(Long mentoId, Long mentiId) throws Exception {
        List<Room> rooms = roomRepository.findAllByWriterOrReceiver(mentoService.findMento(mentoId), mentiService.findMenti(mentiId))
                .orElseThrow(() -> new Exception("room이 존재하지 않습니다/"));
        return rooms;
    }

    private Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    private Room makeRoomObject(Member requestUser, Member otherUser){
        Room room = Room.builder()
                .writer(requestUser)
                .receiver(otherUser)
                .build();
        return room;
    }
}
