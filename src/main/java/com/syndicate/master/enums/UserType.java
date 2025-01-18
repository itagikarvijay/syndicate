package com.syndicate.master.enums;

public enum UserType {

    ADMIN("ADMIN"), TEACHER("USER"), STUDENT("ANONYMOUS");

    private final String type;

    UserType(String string) {
        type = string;
    }

    @Override
    public String toString() {
        return type;
    }
}
