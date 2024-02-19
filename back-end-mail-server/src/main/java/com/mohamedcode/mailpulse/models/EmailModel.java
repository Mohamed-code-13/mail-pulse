package com.mohamedcode.mailpulse.models;

public class EmailModel {
    public Integer emailId;
    public Integer senderId;
    public Integer receiverId;
    public String subject;
    public String description;
    public Integer priority;

    public EmailModel() {}

    public EmailModel(Integer emailId, Integer senderId, Integer receiverId, String subject, String description, Integer priority) {
        this.emailId = emailId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.description = description;
        this.priority = priority;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
