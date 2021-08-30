package ru.rnikonorov.interceptexception;

import lombok.Data;

@Data
public class DataTransferObjectDto {

    private final String info;
    private String exceptionInfo;
    private String interceptionInfo;

}
