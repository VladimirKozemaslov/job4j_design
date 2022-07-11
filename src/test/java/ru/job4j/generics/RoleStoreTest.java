package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("User"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("User"));
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("1", new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Developer"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("10", new Role("10", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("User"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("User"));
    }
}