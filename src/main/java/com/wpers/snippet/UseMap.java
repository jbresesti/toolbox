package com.wpers.snippet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcb on 18/2/17.
 */
public class UseMap {
    public static void main(String[] args) {
        Map<String, Integer> pageVisits = new HashMap<>();

        String page = "http://algomas.com";

        for (int i = 0; i < 10; i++) incrementPageVisit(pageVisits, page);

        for (int i = 0; i < 10; i++) incremetPageFuncional(pageVisits, page);

        for (int i = 0; i < 10; i++) incremetPageFuncional(pageVisits, page);

        System.out.println("cantidad " + pageVisits.get(page));

    }

    private static void incremetPageFuncional(Map<String, Integer> pageVisits, String page) {
        //pageVisits.merge(page, pageVisits.get(page), (key, value) -> value + 1);
        pageVisits.compute(page, (key, value) -> value + 1);
    }

    private static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        if (!pageVisits.containsKey(page)) {
            pageVisits.put(page, 0);
        }

        pageVisits.put(page, pageVisits.get(page) + 1);
    }
}
