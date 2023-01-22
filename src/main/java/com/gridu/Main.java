package com.gridu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        data.readData(args[1]);
        data.createMap();
        Menu menu = new Menu();
        while (true) {
            switch (menu.chooseMenuOption()) {
                case 1 -> {
                    switch (menu.chooseStrategy()) {
                        case "ALL" -> data.printDataByStrategy("ALL");
                        case "ANY" -> data.printDataByStrategy("ANY");
                        case "NONE" -> data.printDataByStrategy("NONE");
                        default -> System.out.println("Please input ALL, ANY or NONE");
                    }
                }
                case 2 -> data.printAllData();
                case 0 -> {
                    System.out.println("\nBye!");
                    return;
                }
            }
        }
    }
}

class Data {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> dataArr = new ArrayList<>();
    Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
    public void createMap() {
        for (int i = 0; i < dataArr.size(); ++i) {
            for (String item : dataArr.get(i).split(" ")) {
                if (map.containsKey(item)) {
                    map.get(item).add(i);
                } else {
                    ArrayList<Integer> integerList = new ArrayList<>();
                    integerList.add(i);
                    map.put(item, integerList);
                }
            }
        }
//        for (String item : map.keySet()) {
//            System.out.println(item + map.get(item).toString());
//        }
    }

    public void readData(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                dataArr.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
    }

    public void printAllData() {
        System.out.println("\n=== List of people ===");
        for (String s : dataArr) {
            System.out.println(s);
        }
    }

    public void printDataByStrategy(String strategy) {
        System.out.println("\nEnter a name or email to search all suitable people.");
        ArrayList<String> foundDataArr = switch (strategy) {
            case "ALL" -> findAll(scanner.nextLine());
            case "ANY" -> findAny(scanner.nextLine());
            case "NONE" -> findNone(scanner.nextLine());
            default -> new ArrayList<>();
        };
        if (foundDataArr.size() == 0) {
            System.out.println("\nNo matching people found.");
        } else {
            for (String s : foundDataArr) {
                System.out.println(s);
            }
        }
    }

    public boolean containsAllString(String string, String request) {
        for (String itemListSplit : string.split("\\s+")) {
            for (String itemFindSplit : request.split("\\s+")) {
                if (itemListSplit.compareToIgnoreCase(itemFindSplit) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean containsString(String string, String request) {
        for (String itemListSplit : string.split("\\s+")) {
            for (String itemFindSplit : request.split("\\s+")) {
                if (itemListSplit.compareToIgnoreCase(itemFindSplit) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> findAll(String request) {
        ArrayList<String> foundDataArr = new ArrayList<>();
        for (String item : dataArr) {
            if (containsAllString(item, request)) {
                foundDataArr.add(item);
            }
        }
        return foundDataArr;
    }

    public ArrayList<String> findAny(String request) {
        ArrayList<String> foundDataArr = new ArrayList<>();
        for (String item : dataArr) {
            if (containsString(item, request)) {
                foundDataArr.add(item);
            }
        }
        System.out.println(foundDataArr);
        return foundDataArr;
    }

    public ArrayList<String> findNone(String request) {
        ArrayList<String> foundDataArr = new ArrayList<>();
        for (String item : dataArr) {
            if (!containsString(item, request)) {
                foundDataArr.add(item);
            }
        }
        return foundDataArr;
    }
}

class Menu {
    Scanner scanner = new Scanner(System.in);
    public int chooseMenuOption() {
        while (true) {
            System.out.println("""

                    === Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit""");
            switch (scanner.nextLine()) {
                case "1" -> {
                    return 1;
                }
                case "2" -> {
                    return 2;
                }
                case "0" -> {
                    return 0;
                }
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    public String chooseStrategy() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }
}