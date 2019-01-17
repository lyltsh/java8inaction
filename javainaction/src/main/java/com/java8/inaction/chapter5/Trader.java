package com.java8.inaction.chapter5;

import lombok.Data;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/1711:15 AM
 */
@Data
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
