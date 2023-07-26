package com.theZ.dotoring.app.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MENTO("ROLE_MENTO"),
    ROLE_MENTI("ROLE_MENTI"),
    ROLE_WAIT("ROLE_WAIT");

    private final String type;
}
