package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.CallTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallTaskRepository extends JpaRepository<CallTask, UUID> {
}