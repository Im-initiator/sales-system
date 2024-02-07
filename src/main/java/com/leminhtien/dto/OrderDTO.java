package com.leminhtien.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderDTO extends BaseDTO{

    private Long userId;
    private String userName;
    private int quantity;

    private TransportDTO transport;
    private String note;

    @Min(value = -1,message = "status must be at least 1")
    @Max(value = 5,message = "status must be at most 5")
    private byte status;

    private String sendAddress;

    private String receAddress;

    private Long[] ids;

    private String recipientName;

    private String phoneNumber;
    private ProductDTO product;
    private String transportCode;



    private String size;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransportCode() {
        return transportCode;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setTransportCode(String transportCode) {
        this.transportCode = transportCode;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TransportDTO getTransport() {
        return transport;
    }

    public void setTransport(TransportDTO transport) {
        this.transport = transport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReceAddress() {
        return receAddress;
    }

    public void setReceAddress(String receAddress) {
        this.receAddress = receAddress;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
