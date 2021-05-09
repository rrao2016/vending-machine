package com.techelevator;

import com.techelevator.io.FileUtility;
import com.techelevator.model.*;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, "Exit"};
    private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Finish Transaction"};
    private static final String[] MONEY_OPTIONS = {"$1", "$2", "$5", "$10", "Back"};

    private Menu menu;
    private Funds funds = new Funds();
    private List<Vendable> products = new ArrayList<>();
    private FileUtility reader = new FileUtility("vendingmachine.csv");
    private FileUtility writer = new FileUtility("Log.txt");


    public VendingMachineCLI(Menu menu) {

        this.menu = menu;
        loadMachine();
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayVendingMachineItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                handlePurchaseOptions();
            } else if (choice.equals("Exit")) {
                System.out.println("**Shutting Down**");
                System.exit(1);
            }
        }
    }

    public void handlePurchaseOptions() {

        boolean stay = true;

        while (stay) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals("Feed Money")) {
                handleFeedMoney();
            } else if (choice.equals("Select Product")) {
                handleProductSelection();
            } else if (choice.equals("Finish Transaction")) {
                handleFinishTransaction();
                stay = false;
            }
        }
    }

    public void handleFeedMoney() {

        boolean stay = true;
        String logMessage = "FEED MONEY: ";
        while (stay) {
            String choice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);
            if (choice.equals("$1")) {
                funds.addBalance(new BigDecimal(1));
                writer.writeAuditFile(logMessage + "$1.00");

            } else if (choice.equals("$2")) {
                funds.addBalance(new BigDecimal(2));
                writer.writeAuditFile(logMessage + "$2.00");

            } else if (choice.equals("$5")) {
                funds.addBalance(new BigDecimal(5));
                writer.writeAuditFile(logMessage + "$5.00");

            } else if (choice.equals("$10")) {
                funds.addBalance(new BigDecimal(10));
                writer.writeAuditFile(logMessage + "$10.00");

            } else if (choice.equals("Back")) {
                stay = false;
            }

            System.out.println(funds);
        }
    }

    public void loadMachine() {

        try {
            this.products = reader.readInventoryFile();
        } catch (FileNotFoundException e) {
            System.out.println("Inventory File Missing. Shutting Down...");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Invalid Product Type. Shutting Down...");
            System.exit(1);
        }
    }

    public void displayVendingMachineItems() {

        for (Vendable vendable : products) {
            System.out.println(vendable);
        }
    }

    public void handleProductSelection() {

        String[] selections = new String[products.size() + 1];

        for (int i = 0; i < products.size(); i++) {
            String selectionLine = products.get(i).toString();
            selections[i] = selectionLine;
        }

        selections[selections.length - 1] = "Back";
        System.out.println(funds);
        String choice = (String) menu.getChoiceFromOptions(selections);

        if (!choice.equals("Back")) {

            String slot = choice.substring(0, 2);

            for (Vendable vendable : products) {

                if (vendable.getSlot().equals(slot)) {

                    boolean hasMoney = funds.hasSufficientFunds(vendable.getPrice());

                    if (!vendable.hasStock()) {
                        System.out.println("Out of Stock.");
                    } else if (!hasMoney) {
                        System.out.println("Not Enough Money!");
                    } else {
                        vendable.sell();
                        funds.subtractFromBalance(vendable.getPrice());
                        System.out.println(vendable.consume());
                        String logMessage = vendable.getSlot() + "|" + vendable.getName() + "|"
                                + vendable.getPrice() + "|" + vendable.getCategory();
                        writer.writeAuditFile(logMessage);
                    }

                    System.out.println(funds);
                }
            }
        }
    }

    public void handleFinishTransaction() {


        int[] changeArr = funds.calculateChange();

        if ( changeArr[0] !=0 ||  changeArr[1] !=0 || changeArr[2] !=0) {

            String changeMsg = "Dispensing Change:\n";

            if (changeArr[0] != 0) {
                changeMsg += changeArr[0] > 1 ? changeArr[0] + " quarters\n" : changeArr[0] + " quarter\n";
            }

            if (changeArr[1] != 0) {
                changeMsg += changeArr[1] > 1 ? changeArr[1] + " dimes\n" : changeArr[1] + " dime\n";
            }

            if (changeArr[2] != 0) {
                changeMsg += changeArr[2] > 1 ? changeArr[2] + " nickels\n" : changeArr[2] + " nickel\n";
            }
            System.out.println(changeMsg);
        }

        funds.resetFunds();
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
