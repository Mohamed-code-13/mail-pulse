package com.mohamedcode.mailpulse.models;

public class Contact {
    private Integer contact_id;
    private String name;
    private String email;

    public Contact() {}

    public Contact(Integer contact_id, String name, String email) {
        this.contact_id = contact_id;
        this.name = name;
        this.email = email;
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
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
