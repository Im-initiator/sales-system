package com.leminhtien.entity;

import com.mysql.cj.protocol.ColumnDefinition;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase_order")
public class OrderEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;
    @Column(name = "date_book")
    private Date dateBook;
    @Column
    private int quantity;
    @Column(columnDefinition = "TEXT")
    private String transport;
    @Column(columnDefinition = "TEXT")
    private String note;
    @Column
    private byte status;
    @Column(columnDefinition = "TEXT")
    private String sendAddress;
    @Column(columnDefinition = "TEXT")
    private String receAddress;

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setDateBook(Date dateBook) {
        this.dateBook = dateBook;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTransport(String transport) {
        this.transport = transport;
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

    public Date getDateBook() {
        return dateBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTransport() {
        return transport;
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
