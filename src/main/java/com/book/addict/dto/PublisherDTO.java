package com.book.addict.dto;

public class PublisherDTO {

    private int idPublisher;
    private String dePublisher;
    private String address;
    private String email;
    private String phoneNumber;

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getDePublisher() {
        return dePublisher;
    }

    public void setDePublisher(String dePublisher) {
        this.dePublisher = dePublisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
