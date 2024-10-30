package ru.job4j.algo.hash;

import java.util.HashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        String longestSubstring = "";
        HashMap<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);
            if (charMap.containsKey(currentChar)) {
                left = charMap.get(currentChar) + 1;
            }
            charMap.put(str.charAt(right), right);
            if (right - left + 1 > longestSubstring.length()) {
                longestSubstring = str.substring(left, right + 1);
            }
        }
        return longestSubstring;
    }

}
