package com.theZ.dotoring.app.room.repository;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.theZ.dotoring.app.letter.domain.QLetter.letter;
import static com.theZ.dotoring.app.room.domain.QRoom.room;

import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.room.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomQueryRepositoryImpl implements RoomQueryRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Room> findRoomsByLatestLetterCreatedDate(Member writer, Member receiver) {

        SubQueryExpression<LocalDateTime> subQuery = select(letter.createdAt.max())
                .from(letter)
                .where(letter.room.eq(room));

        return query
                .selectFrom(room)
                .leftJoin(room.letterList, letter).fetchJoin()
                .where(buildRoomExpression(writer, receiver))
                .orderBy()
                .fetch();
    }

    private BooleanExpression buildRoomExpression(Member writer, Member receiver) {
        BooleanExpression writerExpression = room.writer.eq(writer);
        BooleanExpression receiverExpression = room.receiver.eq(receiver);

        return writerExpression.or(receiverExpression);
    }
}
