package com.email.model;

public class EmailRequest
{
    private String to;
    private String Subject;
    private String message;

    public EmailRequest(String to, String subject, String message) {
        this.to= to;
        Subject = subject;
        this.message = message;
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
                '}';
    }
}
