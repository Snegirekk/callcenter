package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CallTaskRepository extends JpaRepository<CallTask, UUID>, CallTaskRepositoryExtended {

    boolean existsCallTaskByOrder(Order order);
    List<CallTask> findAllByCreatedAtIsBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
