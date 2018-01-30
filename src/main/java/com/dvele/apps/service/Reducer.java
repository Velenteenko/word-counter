package com.dvele.apps.service;

import com.dvele.apps.types.OrderType;

import java.io.IOException;
import java.util.Map;

public interface Reducer {

    Map<String, Integer> reduceWords(byte[] content, String regex) throws IOException;
    Map<String, Integer> reduceMapLimitAndOrder(Map<String, Integer> wordsMap, int limit, OrderType orderType);
}
