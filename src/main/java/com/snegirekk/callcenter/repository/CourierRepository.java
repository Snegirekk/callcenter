package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
}
