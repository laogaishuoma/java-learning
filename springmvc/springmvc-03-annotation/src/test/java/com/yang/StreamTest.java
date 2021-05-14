package com.yang;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {

    }


    @Test
    public void test01() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 1. 过滤数组中为空的元素
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(JSON.toJSON(filtered));

        Random random = new Random();
        // 2. forEach打印每个元素
        random.ints().limit(10).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 3. 获取对应的平方数, 并过滤掉相同的值
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSON(squaresList));

        List<String> strings02 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 4. 获取空字符串的数量
        long count = strings02.stream().filter(string -> string.isEmpty()).count();

        // 5. 对列表进行排序并分别打印
        Random random02 = new Random();
        random02.ints().limit(10).sorted().forEach(System.out::println);

        List<String> strings03 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count03 = strings03.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count03);

        List<String>strings04 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered04 = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> numbers05 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers05.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
