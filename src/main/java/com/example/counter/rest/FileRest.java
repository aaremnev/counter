package com.example.counter.rest;

import com.example.counter.services.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileRest {

    private static final String TEXT_PLAIN = "text/plain";

    @Autowired
    private CounterService counterService;

    @PostMapping
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String text = new String(file.getBytes());
        String type = file.getContentType();

        if (file.isEmpty()) {
            return new ResponseEntity<>("No file provided", HttpStatus.OK);
        }

        if(!TEXT_PLAIN.equalsIgnoreCase(type)) {
            return new ResponseEntity<>("Not allowed content type: " + type, HttpStatus.BAD_REQUEST);
        }

        counterService.process(name, text);
        long result = counterService.getCounter(name);

        return new ResponseEntity<>("Successfully uploaded: " + result, new HttpHeaders(), OK);
    }

}
