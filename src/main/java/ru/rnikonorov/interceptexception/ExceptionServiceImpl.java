package ru.rnikonorov.interceptexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionServiceImpl implements ExceptionService{

    @Override
    public DataTransferObjectDto getInfo() {
        log.trace("Getting info");
        return new DataTransferObjectDto("Info data is here");
    }

    @Override
    public DataTransferObjectDto getException() {
        log.trace("Generating exception");
        throw new RuntimeException("This is runtime exception");
    }
}
