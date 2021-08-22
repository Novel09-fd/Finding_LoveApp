package com.novel.finder.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {


    private String username;
    private String password;
    private String name;
    private String usersex;
    private String address;
    private String age;
    private String phonenumber;
    private String photo;

    public UserModel(){

    }

    public UserModel(String username, String password, String name, String usersex, String address, String age, String phonenumber, String photo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.usersex = usersex;
        this.address = address;
        this.age = age;
        this.phonenumber = phonenumber;
        this.photo = photo;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.name);
        dest.writeString(this.usersex);
        dest.writeString(this.address);
        dest.writeString(this.age);
        dest.writeString(this.phonenumber);
        dest.writeString(this.photo);
    }

    public void readFromParcel(Parcel source) {
        this.username = source.readString();
        this.password = source.readString();
        this.name = source.readString();
        this.usersex = source.readString();
        this.address = source.readString();
        this.age = source.readString();
        this.phonenumber = source.readString();
        this.photo = source.readString();
    }

    protected UserModel(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.name = in.readString();
        this.usersex = in.readString();
        this.address = in.readString();
        this.age = in.readString();
        this.phonenumber = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
