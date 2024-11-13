package com.springrestandcloud.exceptions;

public class SomeGlobalException extends RuntimeException {
    private String resName;
    private String fieldName;
    private Long fieldValue;

    public SomeGlobalException( String resName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resName, fieldName, fieldValue));
        this.resName = resName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Long fieldValue) {
        this.fieldValue = fieldValue;
    }
}
