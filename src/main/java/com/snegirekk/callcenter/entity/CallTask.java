package com.snegirekk.callcenter.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_tasks")
public class CallTask {

    @Id
    private UUID id;

    @Column
    private boolean isDone;

    @Column
    private LocalDateTime createdAt;

    @OneToOne(targetEntity = Order.class)
    private Order order;

    public CallTask() {
        id = UUID.randomUUID();
        isDone = false;
        createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CallTask setCreatedAt(LocalDateTime createdAt) {
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
