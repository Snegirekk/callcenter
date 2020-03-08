package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
