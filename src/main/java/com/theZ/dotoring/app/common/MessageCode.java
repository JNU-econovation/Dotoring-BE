package com.theZ.dotoring.app.common;

public enum MessageCode {

    SUCCESS("4000", "성공");
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
