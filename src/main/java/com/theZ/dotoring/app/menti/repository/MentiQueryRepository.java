package com.theZ.dotoring.app.menti.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
// import com.theZ.dotoring.app.menti.dto.QMentiCardResponseDTO;
import com.theZ.dotoring.app.menti.model.MentiFilterCondition;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static com.theZ.dotoring.app.menti.model.QMenti.*;
import static com.theZ.dotoring.app.mento.model.QMento.mento;

@Repository
@RequiredArgsConstructor
public class MentiQueryRepository{

    private final JPAQueryFactory query;

    public Slice<MentiCardResponseDTO> findAllBySlice(Long lastMentiId, MentiFilterCondition mentiFilterCondition, Pageable pageable) {
        BooleanExpression jobCondition = eqJob(mentiFilterCondition.getJobs());
        BooleanExpression majorCondition = eqMajor(mentiFilterCondition.getMajors());

        if (jobCondition == null) {
            jobCondition = menti.job.isNull();
        }

        if (majorCondition == null) {
            majorCondition = menti.major.isNull();
        }

        List<MentiCardResponseDTO> results = query
                // 아래 방식은 생성자를 통했을 때 가능한 방법
                //.select(new QMentiCardResponseDTO(menti.id, menti.profileImage, menti.nickname, menti.job, menti.major, menti.introduction))
                .select(Projections.bean(MentiCardResponseDTO.class, menti.id, menti.profileImage, menti.nickname, menti.job, menti.major, menti.introduction))
                .from(menti)
                .where(lessThanMentiId(lastMentiId), jobCondition.or(majorCondition))
                .orderBy(menti.job.asc(), menti.major.asc(), menti.id.desc())
                .limit(pageable.getPageSize() + 1)
                .setHint("org.hibernate.readOnly", true)
                .fetch();
        // 무한 스크롤 처리
        return checkLastPage(pageable, results);
    }


    // no-offset 방식 처리하는 메서드
    private BooleanExpression lessThanMentiId(Long mentiId) {
        if (mentiId == null) {
            return null;
        }
        return menti.id.lt(mentiId);
    }

    /**
     *
     * @param jobs
     * @return 멘티의 직무들 중 인자로 받은 직무들 반환
     */
    private BooleanExpression eqJob(List<Job> jobs){
        if (jobs.size() == 0) {
            return null;
        }
        return menti.job.in(jobs);
    }

    /**
     *
     * @param majors
     * @return 멘티의 학과들 중 인자로 받은 학과들 반환
     */
    private BooleanExpression eqMajor(List<Major> majors){
        if (majors.size() == 0) {
            return null;
        }
        return menti.major.in(majors);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<MentiCardResponseDTO> checkLastPage(Pageable pageable, List<MentiCardResponseDTO> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }


}
