package com.efimchick.ifmo.streams.countwords;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Words {

    public static String countWords(List<String> lines) {
        Map<String, Integer> words = lines.stream()
                    .map(line -> line.replaceAll("[^a-zA-Zа-яА-Я]+", " ").split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> word.length() >= 4)
                    .collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));

        Comparator<Map.Entry<String, Integer>> c = Comparator
                    .comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                    .thenComparing(Map.Entry<String, Integer>::getKey);

        return words.entrySet().stream()
                    .filter(entry -> entry.getValue() >= 10)
                    .sorted(c)
                    .map(entry -> String.format("%s - %d\n", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining("")).trim();
    }
}
