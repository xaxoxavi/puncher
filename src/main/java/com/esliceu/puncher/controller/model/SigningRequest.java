package com.esliceu.puncher.controller.model;

public class SigningRequest {
    private String rfid;
    private String idMachine;

    public SigningRequest() {

    }

    public SigningRequest(String rfid, String idMachine) {
        this.rfid = rfid;
        this.idMachine = idMachine;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }
}
