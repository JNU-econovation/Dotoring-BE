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
        Optional<Room> room = roomRepository.findByWriterAndReceiver(writer, receiver);

        if (room.isPresent()) {
            Room roomEntity = room.get();
            roomEntity.updateTime();
            return roomEntity;
        }
        Room newRoom = makeRoomObject(writer, receiver);
        newRoom.updateTime();

        return saveRoom(newRoom);
    }

    @Transactional(readOnly = true)
    public Room findByRoomId(Long roomId) {
        return roomRepository.findById(roomId).isPresent() ?
                roomRepository.findById(roomId).get(): null;
    }

    @Transactional(readOnly = true)
    public List<Room> findAllByUserId(Long mentoId, Long mentiId) {
        List<Room> rooms = roomRepository.findAllByWriterOrReceiver(mentoService.findMento(mentoId), mentiService.findMenti(mentiId));
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
