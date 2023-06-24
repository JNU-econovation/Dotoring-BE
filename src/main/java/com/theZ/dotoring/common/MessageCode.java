package com.theZ.dotoring.common;

public enum MessageCode {

    SUCCESS("2000", "성공"),
    NOT_ALLOWED_FILE_EXT("4003","파일 확장명은 pdf,img만 가능합니다."),
    FILE_NOT_INPUT_OUTPUT("4004","파일 입출력 오류입니다."),
    FILE_NOT_STATE("4005","파일이 이미 이동되었으므로 다른 전송을 시도할 수 없습니다.");

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
