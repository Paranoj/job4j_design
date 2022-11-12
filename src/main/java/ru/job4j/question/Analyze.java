package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();
        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }
        for (User curr : current) {
            if (!map.containsKey(curr.getId())) {
                info.setAdded(info.getAdded() + 1);
            }
            if (map.containsKey(curr.getId()) && !map.containsValue(curr.getName())) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        return new Info(info.getAdded(), info.getChanged(), previous.size() + info.getAdded() - current.size());
    }
}
