package com.theZ.dotoring.app.mento.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.common.DefaultCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.theZ.dotoring.app.mento.model.QMento.mento;

@Repository
@RequiredArgsConstructor
public class MentoQueryRepository {

    private final JPAQueryFactory query;

    public Slice<Mento> findAllBySlice(Long lastMentoId, DefaultCondition defaultCondition, Pageable pageable) {
        List<Mento> results = query.selectFrom(mento)
                .where(lessThanMentoId(lastMentoId),(mento.job.eq(defaultCondition.getJob()).or(mento.major.eq(defaultCondition.getMajor()))))
                .orderBy(mento.job.asc(),mento.major.asc(),mento.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();
        // 무한 스크롤 처리
        return checkLastPage(pageable, results);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression lessThanMentoId(Long mentoId) {
        if (mentoId == null) {
            return null;
        }

        return mento.id.lt(mentoId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<Mento> checkLastPage(Pageable pageable, List<Mento> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
