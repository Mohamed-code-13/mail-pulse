package com.mohamedcode.mailpulse.models;

import java.util.HashMap;
import java.util.Map;

public class EmailModel {
    public Integer emailId;
    public String sender;
    public String receiver;
    public String subject;
    public String body;
    public Integer priority;
    public String sent_date;

    public EmailModel() {}

    public EmailModel(Integer emailId, String sender, String receiver, String subject, String body, Integer priority, String sent_date) {
        this.emailId = emailId;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.priority = priority;
        this.sent_date = sent_date;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSent_date() {
        return sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("email_id", this.getEmailId());
        map.put("sender", this.getSender());
        map.put("receiver", this.getReceiver());
        map.put("subject", this.getSubject());
        map.put("body", this.getBody());
        map.put("priority", this.getPriority());
        map.put("sentDate", this.getSent_date());

        return map;
    }
}
