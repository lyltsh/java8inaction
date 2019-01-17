package com.java8.inaction.chapter5;

import lombok.Data;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/1711:15 AM
 */
@Data
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
