package com.example.counter.rest;

import com.example.counter.exceptions.CounterNotFoundException;
import com.example.counter.services.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterRest {

    private final CounterService counterService;

    @GetMapping
    Map getTotalCounters() {
        return counterService.getTotalCounters();
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
