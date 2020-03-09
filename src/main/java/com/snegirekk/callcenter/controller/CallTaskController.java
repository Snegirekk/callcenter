package com.snegirekk.callcenter.controller;

import com.snegirekk.callcenter.dto.ListCallTaskDto;
import com.snegirekk.callcenter.dto.NewCallTaskDto;
import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import com.snegirekk.callcenter.exception.ApiException;
import com.snegirekk.callcenter.exception.BadRequestException;
import com.snegirekk.callcenter.repository.CallTaskRepository;
import com.snegirekk.callcenter.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Validated
public class CallTaskController extends V1ApiController {

    private CallTaskRepository callTaskRepository;
    private OrderRepository orderRepository;
    private ModelMapper mapper;

    @Autowired
    public CallTaskController(CallTaskRepository callTaskRepository, OrderRepository orderRepository, ModelMapper mapper) {
        this.callTaskRepository = callTaskRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/task")
    public void addCallTask(@RequestBody NewCallTaskDto taskDto) throws Exception {
        Order order = orderRepository
                .findById(taskDto.orderId)
                .orElseThrow(() -> BadRequestException.onNonExistingEntity(
                        Order.class.getSimpleName(), Collections.singletonMap("id", taskDto.orderId.toString())));

        boolean exists = callTaskRepository.existsCallTaskByOrder(order);

        if (exists) {
            throw BadRequestException.onIllegalDuplicate(CallTask.class.getSimpleName(), Collections.singletonMap("orderNumber", order.getOrderNumber()));
        }

        CallTask task = new CallTask();
        task.setOrder(order);

        callTaskRepository.save(task);
    }

    @GetMapping(path = "/task")
    public List<ListCallTaskDto> listCallTasks(
            @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fromDate,
            @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate toDate,
            @Pattern(regexp = "\\d{8}", message = "Order number is an eight-digits string.") @RequestParam(required = false) String orderNumber
    ) throws ApiException {

        if (fromDate.isAfter(toDate)) {
            throw BadRequestException.onInvalidQueryParams("'fromDate' parameter should be less or equal 'toDate' parameter.");
        }

        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.from(LocalTime.MIN));
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.from(LocalTime.MAX));

        List<CallTask> tasks;

        if (null == orderNumber) {
            tasks = callTaskRepository.findAllByCreatedAtIsBetweenOrderByCreatedAtAsc(fromDateTime, toDateTime);
        } else {
            tasks = callTaskRepository.findAllByCreatedAtIsBetweenOrderByCreatedAtAsc(fromDateTime, toDateTime, orderNumber);
        }

        return tasks.stream()
                .map(task -> mapper.map(task, ListCallTaskDto.class))
                .collect(Collectors.toList());
    }
}
