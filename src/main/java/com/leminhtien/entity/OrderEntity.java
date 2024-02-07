package com.leminhtien.entity;
import javax.persistence.*;
@Entity
@Table(name = "purchase_order")
public class OrderEntity extends BaseEntity {

    @Column(name = "recipient_name")
    private String recipientName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", nullable = false)
    private TransportEntity transport;
    @Column(columnDefinition = "TEXT")
    private String note;
    @Column(nullable = false)
    private byte status;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String sendAddress;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String receAddress;

    @Column
    private String phoneNumber;

    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "code")
    private String code;



    @Column(name = "size")
    private String size;

    public String getCode() {
        return code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TransportEntity getTransport() {
        return transport;
    }

    public void setTransport(TransportEntity transport) {
        this.transport = transport;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public void setReceAddress(String receAddress) {
        this.receAddress = receAddress;
    }

    public UserEntity getUser() {
        return user;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }

    public byte getStatus() {
        return status;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public String getReceAddress() {
        return receAddress;
    }


}
