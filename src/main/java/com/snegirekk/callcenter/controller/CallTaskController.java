package com.snegirekk.callcenter.controller;

import com.snegirekk.callcenter.dto.ListCallTaskDto;
import com.snegirekk.callcenter.dto.NewCallTaskDto;
import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import com.snegirekk.callcenter.repository.CallTaskRepository;
import com.snegirekk.callcenter.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        Order order = orderRepository.findById(taskDto.orderId).orElseThrow(() -> new Exception("Order not found."));

        boolean exists = callTaskRepository.existsCallTaskByOrder(order);

        if (exists) {
            throw new Exception("The task is already added.");
        }

        CallTask task = new CallTask();
        task.setOrder(order);

        callTaskRepository.save(task);
    }

    @GetMapping(path = "/task")
    public List<ListCallTaskDto> listCallTasks(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate toDate,
            @RequestParam(required = false) String orderNumber
    ) {

        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.from(LocalTime.MIN));
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.from(LocalTime.MAX));

        List<CallTask> tasks;

        if (null == orderNumber) {
            tasks = callTaskRepository.findAllByCreatedAtIsBetween(fromDateTime, toDateTime);
        } else {
            tasks = callTaskRepository.findAllByCreatedAtIsBetween(fromDateTime, toDateTime, orderNumber);
        }

        return tasks.stream()
                .map(task -> mapper.map(task, ListCallTaskDto.class))
                .collect(Collectors.toList());
    }
}
