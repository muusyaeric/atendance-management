package com.example.atttendancemanagement;

public class Students {
    private String fullname;
    private String faculty;
    private Integer contact;
    private String email;
    private String program;


    public Students(){
        //empty constructor needed for firebase
        //for app not to crash
    }

    public Students(String fullname, String  faculty, Integer contact, String email, String program){
        this.fullname = fullname;
        this.faculty = faculty;
        this.contact = contact;
        this.email = email;
        this.program = program;
    }

    public String getFullname() {
        return fullname;
    }

    public String getFaculty() {
        return faculty;
    }

    public Integer getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getProgram() {
        return program;
    }

}