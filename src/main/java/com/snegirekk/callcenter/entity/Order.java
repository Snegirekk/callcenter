package com.snegirekk.callcenter.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private UUID id;

    @Column(length = 8, unique = true)
    private String orderNumber;

    @ManyToOne(targetEntity = Courier.class)
    private Courier courier;

    public Order() {
        id = UUID.randomUUID();
        orderNumber = "00000000";
    }

    public UUID getId() {
        return id;
    }

    public Order setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Order setOrderNumber(String number) {
        this.orderNumber = number;
        return this;
    }

    public Courier getCourier() {
        return courier;
    }

    public Order setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }
}
