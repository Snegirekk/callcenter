package com.snegirekk.callcenter.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "call_tasks")
public class CallTask {

    @Id
    private UUID id;

    @Column
    private boolean isDone;

    @Column
    private Date createdAt;

    @OneToOne(targetEntity = Order.class)
    private Order order;

    public CallTask() {
        id = UUID.randomUUID();
        isDone = false;
        createdAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public CallTask setId(UUID id) {
        this.id = id;
        return this;
    }

    public boolean isDone() {
        return isDone;
    }

    public CallTask setDone(boolean done) {
        isDone = done;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public CallTask setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public CallTask setOrder(Order order) {
        this.order = order;
        return this;
    }
}
