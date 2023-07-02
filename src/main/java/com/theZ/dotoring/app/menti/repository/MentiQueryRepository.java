package com.theZ.dotoring.app.menti.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.common.DefaultCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static com.theZ.dotoring.app.menti.model.QMenti.*;


@Repository
@RequiredArgsConstructor
public class MentiQueryRepository{

    private final JPAQueryFactory query;


    // 직무가 같은 것 찾고, 학과가 같은 것 찾아서 직무가 같은 것, 학과가 같은 것, 최신 순으로 정렬
    public Slice<Menti> findAllBySlice(Long lastMentiId, DefaultCondition defaultCondition, Pageable pageable) {
        List<Menti> results = query.selectFrom(menti)
                .where(lessThanMentoId(lastMentiId),(menti.job.eq(defaultCondition.getJob()).or(menti.major.eq(defaultCondition.getMajor()))))
                .orderBy(menti.job.asc(),menti.major.asc(),menti.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();
        // 무한 스크롤 처리
        return checkLastPage(pageable, results);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression lessThanMentoId(Long mentiId) {
        if (mentiId == null) {
            return null;
        }

        return menti.id.lt(mentiId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<Menti> checkLastPage(Pageable pageable, List<Menti> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }


}
