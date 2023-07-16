package com.theZ.dotoring.app.room.repository;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.room.domain.Room;

import java.util.List;

public interface RoomQueryRepositoryCustom {

    List<Room> findRoomsByLatestLetterCreatedDate(Member writer, Member receiver);

}
