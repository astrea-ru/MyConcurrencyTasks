package ru.my.tasks.concurrency.gripproject;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class MainApp {

    public static Map<Integer, Integer> sortMap(Map<Integer, Integer> input){
        LinkedHashMap result = new LinkedHashMap();

        input.entrySet().stream().sorted((item1, item2) ->
                item1.getValue()<item2.getValue() ? -1 : 1)
                .forEach(item -> result.put(item.getKey(), item.getValue()));

        return result;
    }

    public static <K, V> Map<K, V> sortMap(Map<K, V> input, Comparator<V> comparable){
        LinkedHashMap result = new LinkedHashMap();

        input.entrySet().stream()
                .sorted((item1, item2) -> comparable.compare(item1.getValue(), item2.getValue()))
                .forEach(item -> result.put(item.getKey(), item.getValue()));

        return result;
    }

    public static void main(String... arg){
        Map<Integer, Integer> input = new HashMap<>();
        input.put(1, 3);
        input.put(2, 1);
        input.put(3, 5);
        input.put(4, 3);
        Map<Integer, Integer> result = sortMap(input);
        Map<Integer, Integer> result2 = sortMap(input, (item1, item2) ->
                item1<item2 ? -1 : 1);
        System.out.println(result.values());
        System.out.println(result2.values());
    }


}
