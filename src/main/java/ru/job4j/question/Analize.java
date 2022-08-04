package ru.job4j.question;

import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added; int changed = 0; int deleted = 0;
        Map<Integer, String> map = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (!current.contains(user)) {
                if (map.containsKey(user.getId())) {
                    changed++;
                } else {
                    deleted++;
                }
            }
        }
        added = (current.size() + deleted) - previous.size();
        return new Info(added, changed, deleted);
    }
}
