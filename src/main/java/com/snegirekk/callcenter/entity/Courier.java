package com.snegirekk.callcenter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "couriers")
public class Courier {

    @Id
    private UUID id;

    public Courier() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Courier setId(UUID id) {
        this.id = id;
        return this;
    }
}
