package com.email.model;

import java.io.File;

public class EmailRequest
{
    private String to;
    private String Subject;
    private String message;
    private File images;

    public File getImages() {
        return images;
    }

    public void setImages(File images) {
        this.images = images;
    }

    public EmailRequest(String to, String subject, String message , File images) {
        this.to= to;
        Subject = subject;
        this.message = message;
        this.images=images;
    }

    public EmailRequest()
    {

    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", Subject='" + Subject + '\'' +
                ", message='" + message + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
