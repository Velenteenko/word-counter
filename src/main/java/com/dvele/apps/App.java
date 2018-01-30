package com.dvele.apps;

import com.dvele.apps.service.FileProcessor;
import com.dvele.apps.service.FileProcessorImpl;
import com.dvele.apps.service.Reducer;
import com.dvele.apps.service.ReducerImpl;
import com.dvele.apps.types.OrderType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "World Count!" );
        if(args.length < 2) {
            throw new IllegalArgumentException("Must be 2 arguments only!");
        }

        OrderType orderType = OrderType.DESC;
        if(args.length >= 3) {
            orderType = OrderType.valueOf(args[2].toUpperCase());
        }

        FileProcessor fileProcessor = new FileProcessorImpl();
        Reducer reducer = new ReducerImpl();
        byte[] fileContent = new byte[0];
        try {
            fileContent = fileProcessor.processFileToByteArray(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String regex = "\\w+";
        Map<String, Integer> afterReducing = new HashMap<>();
        try {
            afterReducing = reducer.reduceWords(fileContent, regex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> resultMap = reducer.reduceMapLimitAndOrder(afterReducing, Integer.valueOf(args[1]), orderType);

        resultMap.forEach((k,v) -> System.out.println(k+" = "+v));
    }
}
