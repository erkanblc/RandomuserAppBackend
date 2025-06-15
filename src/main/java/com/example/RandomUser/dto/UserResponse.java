package com.example.RandomUser.dto;

import com.example.RandomUser.entity.User;

public class UserResponse {
    private Long userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String country;
    private String city;
    private String photo;
    private Long userProfileId;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();

        if (user.getProfile() != null) {
            this.firstName = user.getProfile().getFirstName();
            this.lastName = user.getProfile().getLastName();
            this.gender = user.getProfile().getGender();
            this.country = user.getProfile().getCountry();
            this.city = user.getProfile().getCity();
            this.photo = user.getProfile().getPhoto();
            this.userProfileId = user.getProfile().getId();
        }
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", photo='" + photo + '\'' +
                ", userProfileId=" + userProfileId +
                '}';
    }
}
