package com.example.counter.rest;

import com.example.counter.exceptions.CounterNotFoundException;
import com.example.counter.services.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/counter")
@Slf4j
public class CounterRest {

    @Autowired CounterService counterService;

    @GetMapping
    Map listCounters() {
        return counterService.listAllCounters();
    }

    @GetMapping("/{name:.+}")
    @ResponseBody
    public Map getFileCounters(@PathVariable String name) {
        Map count = counterService.getFileCounters(name);

        if (count == null) {
            throw new CounterNotFoundException(name);
        }

        return count;
    }

}
