package com.example.mini_amazon.enums;

/**
 * @author zhurj
 * @version 1.0 2024/11/2
 */
public enum ENHelpfulType {
    NO_RECORD("0", "无记录"),
    HELP_FUL("1", "有帮助"),
    UN_HELP_FUL("2", "无帮助"),
    ;

    private String value;
    private String label;

    ENHelpfulType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}