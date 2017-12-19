package com.fc.bean;

import java.io.Serializable;

/**
 * @author fangcong on 2017/8/11.
 * public/private/protected/default
 */
public class FieldVO implements Serializable{

    public String field1;
    public Integer field2;

    private String field3;
    private Integer field4;

    protected String field5;
    protected Integer field6;

    String field7;
    Integer field8;

    public FieldVO() {}

    public void method1() {
        System.out.println("no parameterType");
    }

    public void method1(String field1, Integer field2) {
        System.out.println("have parameterTypes : " + field1 + " - " + field2);
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Integer getField2() {
        return field2;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public Integer getField4() {
        return field4;
    }

    public void setField4(Integer field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public Integer getField6() {
        return field6;
    }

    public void setField6(Integer field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public Integer getField8() {
        return field8;
    }

    public void setField8(Integer field8) {
        this.field8 = field8;
    }

    @Override
    public String toString() {
        return "FieldVO{" +
            "field1='" + field1 + '\'' +
            ", field2=" + field2 +
            ", field3='" + field3 + '\'' +
            ", field4=" + field4 +
            ", field5='" + field5 + '\'' +
            ", field6=" + field6 +
            ", field7='" + field7 + '\'' +
            ", field8=" + field8 +
            '}';
    }
}
