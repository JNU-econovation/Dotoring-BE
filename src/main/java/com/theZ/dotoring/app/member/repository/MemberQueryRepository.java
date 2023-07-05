package com.theZ.dotoring.app.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.theZ.dotoring.app.member.model.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public Page<Member> findAllByCondition(Pageable pageable, String condition) {
        List<Member> members = query.selectFrom(member)
                .where(equalCondition(condition), member.status.eq(Status.WAIT))
                .orderBy(member.id.desc())
                .fetch();

        int size = query.selectFrom(member).where(member.status.eq(Status.WAIT)).fetch().size();

        return new PageImpl<>(members,pageable,size);
    }

    private BooleanExpression equalCondition(String condition) {
        if (condition == null) {
            return null;
        }
        return member.dtype.eq(condition);
    }




}
