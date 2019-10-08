package com.example.counter.rest;

import com.example.counter.exceptions.NotSupportedContentException;
import com.example.counter.services.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            throw new NotSupportedContentException("File is empty");
        }

        if(!TEXT_PLAIN.equalsIgnoreCase(type)) {
            throw new NotSupportedContentException("Not allowed content type: " + type);
        }

        counterService.processFile(name, text);

        return new ResponseEntity<>("File uploaded: " + name, new HttpHeaders(), OK);
    }

    @GetMapping
    String[] getFiles() {
        return counterService.getCounterNames();
    }


}
