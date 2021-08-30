package ru.rnikonorov.interceptexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptExceptionRestController {

    private final ExceptionService exceptionService;

    @Autowired
    public InterceptExceptionRestController(final ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    @GetMapping("info")
    @InterceptThis
    public ResponseEntity<DataTransferObjectDto> getInfo(@RequestParam(required = false, name = "exception") final String exception) {
        if ("true".equalsIgnoreCase(exception)) {
            return new ResponseEntity<>(exceptionService.getException(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(exceptionService.getInfo(), HttpStatus.OK);
        }
    }

}
