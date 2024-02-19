package com.mohamedcode.mailpulse.models;

public class EmailModel {
    public Integer emailId;
    public String sender;
    public String receiver;
    public String subject;
    public String description;
    public Integer priority;

    public EmailModel() {}

    public EmailModel(Integer emailId, String sender, String receiver, String subject, String description, Integer priority) {
        this.emailId = emailId;
        this.sender = sender;
        this.receiver = receiver;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
