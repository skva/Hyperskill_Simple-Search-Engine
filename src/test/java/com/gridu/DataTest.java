package com.gridu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @org.junit.jupiter.api.Test
    void createMap() {
        Map<String, ArrayList<Integer>> expectedMap = new LinkedHashMap<>();
        ArrayList<Integer> integerList0 = new ArrayList<>();
        ArrayList<Integer> integerList01 = new ArrayList<>();
        ArrayList<Integer> integerList12 = new ArrayList<>();
        ArrayList<Integer> integerList3 = new ArrayList<>();
        ArrayList<Integer> integerList4 = new ArrayList<>();
        integerList0.add(0);
        expectedMap.put("Dwight", integerList0);
        expectedMap.put("Joseph", integerList0);
        expectedMap.put("djo@gmail.com", integerList0);
        integerList01.add(0);
        integerList01.add(1);
        expectedMap.put("Dwight", integerList01);
        integerList12.add(1);
        integerList12.add(2);
        expectedMap.put("Test", integerList12);
        integerList3.add(3);
        expectedMap.put("Rene", integerList3);
        expectedMap.put("Webb", integerList3);
        expectedMap.put("webb@gmail.com", integerList3);
        integerList4.add(4);
        expectedMap.put("Katie", integerList4);
        expectedMap.put("Jacobs", integerList4);

        Data data = new Data();
        data.readData("test.txt");
        data.createMap();
        Map<String, ArrayList<Integer>> actualMap = data.map;
        assertEquals(expectedMap, actualMap);
    }

    @org.junit.jupiter.api.Test
    void shouldReadData() {
        ArrayList<String> expectedDataArr = new ArrayList<>();
        expectedDataArr.add("Dwight Joseph djo@gmail.com");
        expectedDataArr.add("Dwight Test");
        expectedDataArr.add("Test");
        expectedDataArr.add("Rene Webb webb@gmail.com");
        expectedDataArr.add("Katie Jacobs");
        Data data = new Data();
        data.readData("test.txt");
        assertEquals(expectedDataArr, data.dataArr);
    }

    @org.junit.jupiter.api.Test
    void printAllData() {
        Data data = new Data();
        data.printAllData();
        assertEquals("=== List of people ===", outputStreamCaptor.toString()
                .trim());
    }

    @org.junit.jupiter.api.Test
    void shouldPrintDataByStrategy() {

    }

    @org.junit.jupiter.api.Test
    void shouldContainsAllString() {
        Data data = new Data();
        assertTrue(data.containsAllString("one", "one"));
    }

    @org.junit.jupiter.api.Test
    void shouldNotContainsAllString() {
        Data data = new Data();
        assertFalse(data.containsAllString("one two", "one"));
    }

    @org.junit.jupiter.api.Test
    void shouldContainsString() {
        Data data = new Data();
        assertTrue(data.containsString("one two", "one"));
    }

    @org.junit.jupiter.api.Test
    void shouldNotContainsString() {
        Data data = new Data();
        assertFalse(data.containsString("one two", "three"));
    }

    @org.junit.jupiter.api.Test
    void shouldFindAll() {
        Data data = new Data();
        data.readData("test.txt");
        ArrayList<String> expectedDataArr = new ArrayList<>();
        expectedDataArr.add("Test");
        assertEquals(expectedDataArr, data.findAll("Test"));
    }

    @org.junit.jupiter.api.Test
    void shouldFindAny() {
        Data data = new Data();
        data.readData("test.txt");
        ArrayList<String> expectedDataArr = new ArrayList<>();
        expectedDataArr.add("Dwight Joseph djo@gmail.com");
        expectedDataArr.add("Dwight Test");
        assertEquals(expectedDataArr, data.findAny("Dwight"));
    }

    @org.junit.jupiter.api.Test
    void findNone() {
        Data data = new Data();
        data.readData("test.txt");
        assertFalse(data.findNone("Test").contains("Test"));
    }
}