package com.theZ.dotoring.app.certification.repository;

import com.theZ.dotoring.app.certification.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findAllByOrdOrderByCreatedAtDesc();
}
