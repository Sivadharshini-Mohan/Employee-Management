package com.i2i.annotation.common;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Others("Others");
    
    public String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}