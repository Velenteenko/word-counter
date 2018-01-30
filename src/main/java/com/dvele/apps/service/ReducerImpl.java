package com.dvele.apps.service;

import com.dvele.apps.types.OrderType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReducerImpl implements Reducer {

    @Override
    public Map<String, Integer> reduceWords(byte[] content, String regex) throws IOException {

        StringBuilder builder = new StringBuilder(new String(content, "UTF-8"));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(builder.toString().replaceAll("'", ""));
        List<String> allWords = new ArrayList<>();
        while (matcher.find()) {
            allWords.add(matcher.group().toLowerCase());
        }

        Map<String, Integer> map = new HashMap<>();
        allWords.forEach( t -> {
            Integer count = map.get(t);
            map.put(t, (count == null) ? 1 : count + 1 );
        });

        return map;
    }

    @Override
    public Map<String, Integer> reduceMapLimitAndOrder(Map<String, Integer> wordsMap, int limit, OrderType orderType) {

        Map<String, Integer> mapAfterSort;

        int mapLimit = limit;
        if(wordsMap.size() >= limit) {
            mapLimit = wordsMap.size();
        }

        if(orderType == OrderType.ASC) {
            mapAfterSort = wordsMap.entrySet().stream()
                    .filter(map -> map.getValue() >= limit)
                    .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            return mapAfterSort;
        }

        mapAfterSort = wordsMap.entrySet().stream()
                .filter(map -> map.getValue() >= limit)
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));



        return mapAfterSort;
    }
}
