package com.mohamedcode.mailpulse.models;

import java.util.HashMap;
import java.util.Map;

public class Contact {
    private Integer contactId;
    private String name;
    private String email;

    public Contact(Integer contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
