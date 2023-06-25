package com.theZ.dotoring.app.member.repository;

import com.theZ.dotoring.app.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
