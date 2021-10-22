package com.project.nice;

public class User {

    String Email;
    String Password;
    String Name;

    public User(){


    }

    public User(String Email, String Password, String Name){

        this.Email = Email;
        this.Password = Password;
        this.Name = Name;

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }

    /* String id;
    String pw;
    String name;
    String tel;
    String adr;*/

    /*public User(){

    }

    public User(String id, String pw, String name, String tel, String adr) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.adr = adr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", adr='" + adr + '\'' +
                '}';
    }*/
}
