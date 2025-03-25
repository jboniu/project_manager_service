package com.javanoteany.common.query;

public class Search {

    /**
     * 根据条件匹配的字段
     */
    private String fields;

    /**
     * 根据匹配的value
     */
    private String value;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
