package com.gridu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
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
    @Test
    void shouldChooseMenuOptionZero() {
        String input = "0";
        InputStream in0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in0);
        Menu menu = new Menu();
        assertEquals(0, menu.chooseMenuOption());
    }

    @Test
    void shouldChooseMenuOptionOne() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        assertEquals(1, menu.chooseMenuOption());
    }

    @Test
    void shouldChooseMenuOptionTwo() {
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        assertEquals(2, menu.chooseMenuOption());
    }

    @Test
    void shouldChooseMenuOptionIncorrect() {
        String input = "5\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outputStreamCaptor));

        Menu menu = new Menu();
        menu.chooseMenuOption();

        assertEquals("""
                    === Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit""" + "\n\nIncorrect option! Try again.\n\n" +
                """
                    === Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit""", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void shouldChooseStrategy() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        assertEquals("1", menu.chooseStrategy());
    }
}
