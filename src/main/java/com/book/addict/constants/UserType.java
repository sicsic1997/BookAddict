package com.book.addict.constants;

import org.apache.catalina.User;

import javax.jws.soap.SOAPBinding;

public enum UserType {
    CUSTOMER(1),
    ADMIN(2),
    SUPER_ADMIN(3);

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    UserType(int i) {
        id = i;
    }

    public static UserType getUserTypeByName(String name) {
        switch (name) {
            case "CUSTOMER":
                return UserType.CUSTOMER;
            case "ADMIN":
                return UserType.ADMIN;
            case "SUPER_ADMIN":
                return UserType.SUPER_ADMIN;
            default:
                return UserType.CUSTOMER;
        }
    }

}
