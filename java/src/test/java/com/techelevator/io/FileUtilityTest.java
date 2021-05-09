package com.techelevator.io;

import com.techelevator.model.Vendable;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileUtilityTest {

    @Test
    public void readInventoryFile() {

        FileUtility inTest = new FileUtility("vendingmachine.csv");
        try {
            List<Vendable> dataReadFromFile = inTest.readInventoryFile();
            Assert.assertEquals(16,  dataReadFromFile.size());

        } catch (FileNotFoundException e) {
            Assert.fail("Should be able to read file");
        }

    }

    @Test
    public void writeAuditFile() {

        FileUtility outTest = new FileUtility("out-test.txt");

        String testMessage = "Hello World!" + LocalDateTime.now();
        outTest.writeAuditFile(testMessage);

        File testFileAfterWrite = outTest.getFile();
        String lineOfText = "";
        try {
            Scanner scanner = new Scanner(testFileAfterWrite);
            while (scanner.hasNext()) {
                lineOfText += scanner.nextLine();
            }
            boolean containsMessage = lineOfText.contains(testMessage);
            Assert.assertTrue(containsMessage);

        } catch (FileNotFoundException e) {
            Assert.fail("Should be able to read file");
        }
    }
}