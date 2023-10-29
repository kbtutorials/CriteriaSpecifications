package com.filter.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Student#Student()}
     *   <li>{@link Student#setAddress(String)}
     *   <li>{@link Student#setHobby(String)}
     *   <li>{@link Student#setId(Long)}
     *   <li>{@link Student#setName(String)}
     *   <li>{@link Student#getAddress()}
     *   <li>{@link Student#getHobby()}
     *   <li>{@link Student#getId()}
     *   <li>{@link Student#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Student actualStudent = new Student();
        actualStudent.setAddress("42 Main St");
        actualStudent.setHobby("Hobby");
        actualStudent.setId(1L);
        actualStudent.setName("Name");
        String actualAddress = actualStudent.getAddress();
        String actualHobby = actualStudent.getHobby();
        Long actualId = actualStudent.getId();
        String actualName = actualStudent.getName();
        assertEquals("42 Main St", actualAddress);
        assertEquals("Hobby", actualHobby);
        assertEquals(1L, actualId.longValue());
        assertEquals("Name", actualName);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Student#Student(Long, String, String, String)}
     *   <li>{@link Student#setAddress(String)}
     *   <li>{@link Student#setHobby(String)}
     *   <li>{@link Student#setId(Long)}
     *   <li>{@link Student#setName(String)}
     *   <li>{@link Student#getAddress()}
     *   <li>{@link Student#getHobby()}
     *   <li>{@link Student#getId()}
     *   <li>{@link Student#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Student actualStudent = new Student(1L, "Name", "42 Main St", "Hobby");
        actualStudent.setAddress("42 Main St");
        actualStudent.setHobby("Hobby");
        actualStudent.setId(1L);
        actualStudent.setName("Name");
        String actualAddress = actualStudent.getAddress();
        String actualHobby = actualStudent.getHobby();
        Long actualId = actualStudent.getId();
        String actualName = actualStudent.getName();
        assertEquals("42 Main St", actualAddress);
        assertEquals("Hobby", actualHobby);
        assertEquals(1L, actualId.longValue());
        assertEquals("Name", actualName);
    }
}

