package com.university.registration.model;

import java.util.Date;

public class Registration {
    private int regId;
    private int studentId;
    private int courseId;
    private Date registrationDate;

    public Registration() {}

    public Registration(int regId, int studentId, int courseId, Date registrationDate) {
        this.regId = regId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }
    public int getRegId() { return regId; }
    public void setRegId(int regId) { this.regId = regId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
}