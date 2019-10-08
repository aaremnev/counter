package com.example.counter.rest;

import com.example.counter.services.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileRest {

    private static final String TEXT_PLAIN = "text/plain";

    @Autowired
    private CounterService counterService;

    @PostMapping
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        String name = file.getOriginalFilename();
        String text = new String(file.getBytes());
        String type = file.getContentType();

        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", OK);
        }

        if(!TEXT_PLAIN.equalsIgnoreCase(type)) {
            return new ResponseEntity<>("Not allowed content type: " + type, BAD_REQUEST);
        }

        counterService.process(name, text);
        long result = counterService.getCounter(name);

        String msg = String.format("Successfully uploaded: %s (%d)", name, result);
        return new ResponseEntity<>(msg, new HttpHeaders(), OK);
    }

}
