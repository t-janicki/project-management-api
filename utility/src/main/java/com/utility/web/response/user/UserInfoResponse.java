package com.utility.web.response.user;

public class UserInfoResponse {
    //    private Long createdAt;
//    private Long updatedAt;
//    private String createdByUserId;
//    private String updatedByUserId;
    private Long id;
    private String role;
    private String name;
    private String imageUrl;
    private String email;
    private String phone;
    private Boolean isActive;
    private Boolean emailVerified;
    private String jobTitle;

    public UserInfoResponse() {
    }

    public UserInfoResponse(Long id, String role,
                            String name, String imageUrl,
                            String email, String phone,
                            Boolean isActive, Boolean emailVerified,
                            String jobTitle) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.emailVerified = emailVerified;
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}