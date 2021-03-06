package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.CallTask;

import java.time.LocalDateTime;
import java.util.List;

public interface CallTaskRepositoryExtended {

    List<CallTask> findAllByCreatedAtIsBetweenOrderByCreatedAtAsc(LocalDateTime fromDate, LocalDateTime toDate, String orderNumber);
}
