/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea.beans;


import java.util.Date;

/**
 *
 * @author SS.555
 */
public class Student {
    
    private long id;
    private String name;
    private String surname;
    private String parent;
    private Date birthdate;
    private String phone;
    private String gmail;
    private String gmailCode;
    private String groupName;
    private String lessonDate;
    private String lessonTime;
    private Double payment;
    private String status;

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getGmailCode() {
        return gmailCode;
    }

    public void setGmailCode(String gmailCode) {
        this.gmailCode = gmailCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
        public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", parent=" + parent + ", birthdate=" + birthdate + ", phone=" + phone + ", gmail=" + gmail + ", gmailCode=" + gmailCode + ", groupName=" + groupName + ", lessonDate=" + lessonDate + ", lessonTime=" + lessonTime + ", payment=" + payment + ", status=" + status + '}';
    }

    

    
    

    

    

}
