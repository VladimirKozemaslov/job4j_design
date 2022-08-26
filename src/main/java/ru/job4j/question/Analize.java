package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    private static boolean isChange(Set<User> set, User user) {
        boolean rsl = false;
        for (User checkUser : set) {
            if (checkUser.getId() == user.getId()) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<User> addedAndChanged = new HashSet<>(current);
        Set<User> deletedAndChanged = new HashSet<>(previous);
        addedAndChanged.removeAll(previous);
        deletedAndChanged.removeAll(current);
        int changed = 0;
        for (User user : addedAndChanged) {
            if (isChange(previous, user)) {
                changed++;
            }
        }
        info.setAdded(addedAndChanged.size() - changed);
        info.setDeleted(deletedAndChanged.size() - changed);
        info.setChanged(changed);
        return info;
    }

}