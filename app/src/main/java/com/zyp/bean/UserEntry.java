package com.zyp.bean;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class UserEntry extends BaseEntry {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
