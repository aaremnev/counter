package com.example.counter.rest;

import com.example.counter.exceptions.CounterNotFoundException;
import com.example.counter.services.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/counter")
@Slf4j
public class CounterRest {

    @Autowired CounterService counterService;

    @GetMapping
    Collection<String> listCounters() {
        return counterService.listCounters();
    }

    @GetMapping("/{name:.+}")
    @ResponseBody
    public Long getCounter(@PathVariable String name) {
        Long count = counterService.getCounter(name);
        if (count == null) {
            throw new CounterNotFoundException(name);
        }
        return count;
    }

}
