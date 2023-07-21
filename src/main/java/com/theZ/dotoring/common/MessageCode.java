package com.theZ.dotoring.common;

public enum MessageCode {

    SUCCESS("2000", "성공"),
    NOT_ALLOWED_FILE_EXT("4003","파일 확장명은 pdf,img만 가능합니다."),
    FILE_NOT_INPUT_OUTPUT("4004","파일 입출력 오류입니다."),
    FILE_NOT_STATE("4005","파일이 이미 이동되었으므로 다른 전송을 시도할 수 없습니다."),
    FIlE_NOT_FOUND("4006","파일이 존재하지 않습니다."),

    DUPLICATED_NICKNAME("4009","중복된 닉네임이 존재합니다."),
    DUPLICATED_LOGIN_ID("4010","중복된 아이디가 존재합니다."),

    WRONG_CODE("4077","잘못된 코드입니다."),

    VALIDATION_FAIL("4011","유효성 검증 실패"),

    LIMIT_FILE_SIZE("4012","파일 사이즈는 20MB 입니다."),

    ROOM_NOT_FOUND("4013","채팅방이 존재하지 않습니다."),
    
    LETTER_NOT_FOUND("4014","쪽지가 존재하지 않습니다."),

    ENUM_NOT_FOUND("4015","해당 enum이 존재하지 않습니다.");

    private final String code;
    private final String value;

    MessageCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }


}
