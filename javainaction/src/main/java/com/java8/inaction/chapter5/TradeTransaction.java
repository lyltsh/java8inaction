package com.java8.inaction.chapter5;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/1711:17 AM
 */
public class TradeTransaction {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1、2011发生的所有交易，交易额排序
        System.out.println("1===========");
        List<Transaction> transactions2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        transactions2011.forEach(System.out::println);

        //2、交易员都在哪些不同城市工作过
        System.out.println("2===========");
        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
        Set<String> cities = traders.stream()
                .map(Trader::getCity)
                .collect(Collectors.toSet());
        cities.forEach(System.out::println);

        //3、查找所有来自剑桥的交易员，并按照姓名字母顺序排序
        System.out.println("3===========");
        List<Trader> CambridgeTrader = traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        CambridgeTrader.forEach(System.out::println);

        //4、返回所有交易员的姓名字符串，并按照姓名字母顺序排序
        System.out.println("4===========");
        List<String> traderName = traders.stream()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
        traderName.forEach(System.out::println);

        //5、有没有交易员在米兰工作？
        System.out.println("5===========");
        boolean traderInMilan = traders.stream()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(traderInMilan);

        //6、打印生活在剑桥的交易员的所有交易额
        System.out.println("6===========");
        Optional<Integer> totalValueInCambridge = traders.stream()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .map(trader -> {
                    return transactions.stream()
                            .filter(transaction -> transaction.getTrader() == trader)
                            .map(Transaction::getValue)
                            .reduce(Integer::sum).get();
                })
                .reduce(Integer::sum);
        System.out.println(totalValueInCambridge);


        //7、所有交易中，最高的交易额是多少
        System.out.println("7===========");
        Integer maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get();
        System.out.println(maxValue);

        //8、所有交易中，做小的交易额是多少
        System.out.println("8===========");
        Integer minValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .get();
        System.out.println(minValue);
    }
}
