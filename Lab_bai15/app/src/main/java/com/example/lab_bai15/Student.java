package com.example.lab_bai15;

public class Student {
    private int id;
    private String name;
    private String address;
    private String phone_number;
    public Student(int id, String name, String address, String phone_number) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone_number() {
        return phone_number;
    }
}
