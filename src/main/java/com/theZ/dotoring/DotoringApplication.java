package com.theZ.dotoring;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.repository.LetterRepository;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.app.room.domain.Room;
import com.theZ.dotoring.app.room.repository.RoomRepository;
import com.theZ.dotoring.enums.DeleteStatus;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DotoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DotoringApplication.class, args);
	}

}

