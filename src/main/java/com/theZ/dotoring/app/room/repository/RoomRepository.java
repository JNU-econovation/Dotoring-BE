package com.theZ.dotoring.app.room.repository;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByWriterOrReceiver(Member writer, Member receiver);

    Optional<Room> findByWriterOrReceiver(Member writer, Member receiver);

    Optional<Room> findByWriterAndReceiver(Member writer, Member receiver);
}
