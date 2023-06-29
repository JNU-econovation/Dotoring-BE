package com.theZ.dotoring.app.certification.repository;

import com.theZ.dotoring.app.certification.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    @Query("SELECT c FROM Certification c ORDER BY c.createdAt DESC")
    List<Certification> findAllByOrderByCreatedAtDesc();
}
