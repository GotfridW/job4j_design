package ru.job4j.algo.stack;

import java.util.Map;
import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        boolean isValid = true;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> brackets = Map.of('(', ')', '{', '}', '[', ']');
        for (char c : s.toCharArray()) {
            if (brackets.containsKey(c)) {
                stack.add(brackets.get(c));
            } else if (stack.isEmpty() || stack.pop() != c) {
                isValid = false;
                break;
            }
        }
        return isValid && stack.isEmpty();
    }
}
