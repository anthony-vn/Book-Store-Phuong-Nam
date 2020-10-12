package com.example.bookstorephuongnam.Modal;

public class NguoiDung {
    private int id;
    private String userName;
    private String password;
    private String phone;

    public NguoiDung() {
    }

    public NguoiDung(int id, String userName, String password, String phone) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public NguoiDung(String userName, String password, String phone) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
