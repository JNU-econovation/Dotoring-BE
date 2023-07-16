package com.theZ.dotoring.app.mento.repository;

import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.model.MentoFilterCondition;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
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

    public Slice<MentoCardResponseDTO> findAllBySlice(Long lastMentoId, MentoFilterCondition mentoFilterCondition, Pageable pageable) {
        BooleanExpression jobCondition = eqJob(mentoFilterCondition.getJobs());
        BooleanExpression majorCondition = eqMajor(mentoFilterCondition.getMajors());

        if (jobCondition == null) {
            jobCondition = mento.job.isNull();
        }

        if (majorCondition == null) {
            majorCondition = mento.major.isNull();
        }

        List<MentoCardResponseDTO> results = query
                .select(Projections.bean(MentoCardResponseDTO.class, mento.id, mento.profileImage, mento.nickname, mento.job, mento.major, mento.introduction))
                .from(mento)
                .where(lessThanMentoId(lastMentoId), jobCondition.or(majorCondition))
                .orderBy(mento.job.asc(), mento.major.asc(), mento.id.desc())
                .limit(pageable.getPageSize() + 1)
                .setHint("org.hibernate.readOnly", true)
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

    private BooleanExpression eqJob(List<Job> jobs){
        if (jobs.size() == 0) {
            return null;
        }
        return mento.job.in(jobs);

    }

    private BooleanExpression eqMajor(List<Major> majors){
        if (majors.size() == 0) {
            return null;
        }
        return mento.major.in(majors);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<MentoCardResponseDTO> checkLastPage(Pageable pageable, List<MentoCardResponseDTO> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
