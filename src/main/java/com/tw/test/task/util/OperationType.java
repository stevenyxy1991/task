package com.tw.test.task.util;

public enum OperationType {

    MEMORY("MEMORY"),

    JPA("JPA");

    private String info;

    private OperationType(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }

    public static OperationType getType(String info){
        switch (info){
            case "JPA":
                return JPA;
            case "MEMORY":
                return MEMORY;
            default:
                throw new IllegalStateException("Unexpected value: " + info);
        }
    }



}
