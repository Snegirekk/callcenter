package com.snegirekk.callcenter.controller;

import com.snegirekk.callcenter.dto.ListCallTaskDto;
import com.snegirekk.callcenter.dto.NewCallTaskDto;
import com.snegirekk.callcenter.entity.CallTask;
import com.snegirekk.callcenter.entity.Order;
import com.snegirekk.callcenter.repository.CallTaskRepository;
import com.snegirekk.callcenter.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<ListCallTaskDto> listCallTasks() {
        List<CallTask> tasks = callTaskRepository.findAll();

        return tasks.stream()
                .map(task -> mapper.map(task, ListCallTaskDto.class))
                .collect(Collectors.toList());
    }
}
