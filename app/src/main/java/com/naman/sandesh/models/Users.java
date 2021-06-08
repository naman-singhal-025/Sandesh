package com.naman.sandesh.models;

public class Users {
    String profilePic;
    String userName;
    String mail;
    String userId;
    String password;
    String lastMessage;

    public Users(String profilePic, String userName, String mail, String userId, String password, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.userId = userId;
        this.password = password;
        this.lastMessage = lastMessage;
    }

    public Users() {
    }


    //Sign Up constructor
    public Users(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
