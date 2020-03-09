package com.snegirekk.callcenter.repository;

import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class CallTaskRepositoryExtendedImpl implements CallTaskRepositoryExtended {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CallTask> findAllByCreatedAtIsBetween(LocalDateTime fromDate, LocalDateTime toDate, String orderNumber) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CallTask> criteriaQuery = criteriaBuilder.createQuery(CallTask.class);

        Root<CallTask> callTask = criteriaQuery.from(CallTask.class);
        Join<CallTask, Order> order = callTask.join("order");

        criteriaQuery
                .where(criteriaBuilder.between(callTask.get("createdAt"), fromDate, toDate))
                .where(criteriaBuilder.equal(order.get("orderNumber"), orderNumber));

        TypedQuery<CallTask> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
