package com.theZ.dotoring.app.letter.mapper;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberRequestDTO;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.member.model.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-08T22:23:51+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class LetterMapperImpl implements LetterMapper {

    @Override
    public Letter toEntity(LetterByMemberRequestDTO letterRequestDTO, Member member, Date date) {
        if ( letterRequestDTO == null && member == null && date == null ) {
            return null;
        }

        Letter.LetterBuilder letter = Letter.builder();

        if ( letterRequestDTO != null ) {
            letter.content( letterRequestDTO.getContent() );
        }
        if ( member != null ) {
            letter.writer( member );
            letter.id( member.getId() );
        }
        letter.createdAt( date );

        return letter.build();
    }

    @Override
    public ArrayList<LetterByMemberResponseDTO> toDTOs(List<Letter> letter, Member member) {
        if ( letter == null ) {
            return null;
        }

        ArrayList<LetterByMemberResponseDTO> arrayList = new ArrayList<LetterByMemberResponseDTO>();
        for ( Letter letter1 : letter ) {
            arrayList.add( toDTO( letter1, member ) );
        }

        return arrayList;
    }

    @Override
    public LetterByMemberResponseDTO toDTO(Letter letter, Member member) {
        if ( letter == null ) {
            return null;
        }

        LetterByMemberResponseDTO.LetterByMemberResponseDTOBuilder letterByMemberResponseDTO = LetterByMemberResponseDTO.builder();

        letterByMemberResponseDTO.letterId( letter.getId() );
        letterByMemberResponseDTO.createdAt( letter.getCreatedAt() );
        letterByMemberResponseDTO.writer( map( letterWriterId( letter ), member ) );
        letterByMemberResponseDTO.content( letter.getContent() );

        return letterByMemberResponseDTO.build();
    }

    private Long letterWriterId(Letter letter) {
        if ( letter == null ) {
            return null;
        }
        Member writer = letter.getWriter();
        if ( writer == null ) {
            return null;
        }
        Long id = writer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
