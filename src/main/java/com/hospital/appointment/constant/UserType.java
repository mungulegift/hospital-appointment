package com.hospital.appointment.constant;

public enum UserType {
    ADMIN(1),
    PROVIDER(2),
    PATIENT(3);

    int id;

    UserType(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
