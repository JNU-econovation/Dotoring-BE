package com.theZ.dotoring.app.menti.repository;

import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.model.Mento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentiRepository extends JpaRepository<Menti,Long> {

    Optional<Menti> findByEmail(String email);
}
