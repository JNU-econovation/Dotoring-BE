package com.theZ.dotoring.app.mento.repository;

import com.theZ.dotoring.app.mento.model.Mento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentoRepository extends JpaRepository<Mento,Long> {

    Optional<Mento> findByEmail(String email);
}
