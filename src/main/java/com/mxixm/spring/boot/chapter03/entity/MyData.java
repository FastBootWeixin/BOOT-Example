package com.mxixm.spring.boot.chapter03.entity;

import javax.xml.bind.annotation.XmlRootElement;

// 测试的数据定义
@XmlRootElement // 标记该类型可被转换为XML
public class MyData {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}