package com.javarush.task.task17.task1721;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        String fileName_2 = scan.nextLine();
        readFile(fileName, allLines);
        readFile(fileName_2, forRemoveLines);
        Solution solution = new Solution();
        solution.joinData();
    }

    private static void readFile(String fileName, List<String> list) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
