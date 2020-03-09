package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class CallTaskRepositoryExtendedImpl implements CallTaskRepositoryExtended {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CallTask> findAllByCreatedAtIsBetweenOrderByCreatedAtAsc(LocalDateTime fromDate, LocalDateTime toDate, String orderNumber) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CallTask> criteriaQuery = criteriaBuilder.createQuery(CallTask.class);

        Root<CallTask> callTask = criteriaQuery.from(CallTask.class);
        Join<CallTask, Order> order = callTask.join("order");

        Predicate createdBetween = criteriaBuilder.between(callTask.get("createdAt"), fromDate, toDate);
        Predicate orderNumberEqual = criteriaBuilder.equal(order.get("orderNumber"), orderNumber);
        javax.persistence.criteria.Order orderBy = criteriaBuilder.asc(callTask.get("createdAt"));

        criteriaQuery.where(createdBetween, orderNumberEqual).orderBy(orderBy);

        TypedQuery<CallTask> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
