package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<User> addedAndChanged = new HashSet<>(current);
        Set<User> deletedAndChanged = new HashSet<>(previous);
        addedAndChanged.removeAll(previous);
        deletedAndChanged.removeAll(current);
        int changed = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User user : addedAndChanged) {
            map.put(user.getId(), user.getName());
        }
        for (User user : deletedAndChanged) {
            if (map.containsKey(user.getId())) {
                changed++;
            }
        }
        info.setAdded(addedAndChanged.size() - changed);
        info.setDeleted(deletedAndChanged.size() - changed);
        info.setChanged(changed);
        return info;
    }

}