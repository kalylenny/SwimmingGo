package com.example.swimminggo.models;

import org.json.JSONObject;

public abstract class User {
    private String username, dob, email, firstName, lastName, phone, roleName;
    private int gender, id;
    private int isVerified;

    public User(){
        username = dob = email = firstName = lastName = phone = "";
    }
    public User(String username, String dob, String email, String firstName, String lastName, String phone, int gender, int id, String roleName, int isVerified) {
        this.username = username;
        this.dob = dob;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.id = id;
        this.roleName = roleName;
        this.isVerified = isVerified;
    }

    public String getUsername() {
        return username;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUser(String dob, String email,String firstName, String lastName, String phone, int gender, int id, String roleName, int isVerified ){
        setDob(dob);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setGender(gender);
        setId(id);
        setRoleName(roleName);
        setIsVerified(isVerified);
    }

    public String getFullName(){
        if (lastName.equals("null") || firstName.equals("null"))
            return "Chưa cập nhật";
        return firstName + " " + lastName;

    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public abstract void getDataFromJSONObject(JSONObject jsonObject);
    public abstract JSONObject convertToJSONObject();

}
