package com.example.atttendancemanagement;

public class Lecturers {
    private String fullname;
    private String faculty;
    private String address;
    private Integer contact;
    private String email;

    public Lecturers(){
        //empty constructor needed for firebase
        //for app not to crash
    }

    public Lecturers(String fullname, String  faculty, String adddress, Integer contact, String email){
        this.fullname = fullname;
        this.faculty = faculty;
        this.address = adddress;
        this.contact = contact;
        this.email = email;

    }


    public String getFullname() {
        return fullname;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getAddress() {
        return address;
    }

    public Integer getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

}

