package com.example.hunglv.demo_webserver;

public class Student {
    public String id;
    public String name;
    public String date;
    public String address;
    public Student(String id, String name, String date, String address) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
    }
    public Student() {

    }
    public String toString() {
        return "id = " + id + "name = " + name + "date = " + date + "address = " + address;
    }
}
