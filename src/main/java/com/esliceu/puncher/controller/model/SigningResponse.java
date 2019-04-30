package com.esliceu.puncher.controller.model;

public class SigningResponse {

    private Status status;
    private String photo;
    private String message;

    public SigningResponse(Status status, String picture, String message) {
        this.message = message;
        this.status = status;
        this.photo = picture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
