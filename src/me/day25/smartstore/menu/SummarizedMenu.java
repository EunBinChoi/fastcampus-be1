package me.day25.smartstore.menu;

import me.day25.smartstore.customers.ClassifiedCustomersGroup;
import me.day25.smartstore.customers.Customer;
import me.day25.smartstore.customers.Customers;
import me.day25.smartstore.exception.InputEmptyException;
import me.day25.smartstore.exception.InputEndException;
import me.day25.smartstore.exception.InputRangeException;
import me.day25.smartstore.util.Message;

import java.util.Comparator;


public class SummarizedMenu extends Menu {

    /////////////////////////////////////////
    ////////////// singleton ////////////////
    private static SummarizedMenu summarizedMenu;

    public static SummarizedMenu getInstance() {
        if (summarizedMenu == null) {
            summarizedMenu = new SummarizedMenu();
        }
        return summarizedMenu;
    }
    /////////////////////////////////////////
    /////////////////////////////////////////

    private Customers allCustomers = Customers.getInstance();

    private ClassifiedCustomersGroup classifiedCusGroup = ClassifiedCustomersGroup.getInstance();


//    static int dispSummaryMenu() {
//        while (true) {
//            try {
//                System.out.println();
//                System.out.println("==============================");
//                System.out.println(" 1. Summary");
//                System.out.println(" 2. Summary (Sorted By Name)");
//                System.out.println(" 3. Summary (Sorted By Spent Time)");
//                System.out.println(" 4. Summary (Sorted By Total Payment)");
//                System.out.println(" 5. Back");
//                System.out.println("==============================");
//                System.out.print("Choose One: ");
//                int choice = Integer.parseInt(Menu.nextLine());
//                if (choice >= 1 && choice <= 5) {
//                    return choice;
//                }
//                throw new InputRangeException();
//            } catch (NumberFormatException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
//            } catch (InputRangeException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
//            }
//        }
//    }

    public void manageSummaryMenu() { // customer set group => classified by group
        classifiedCusGroup = allCustomers.classified();
        //System.out.println("Arrays.toString(classifiedCusGroup.getClassifiedCustomers()) = " + Arrays.toString(classifiedCusGroup.getClassifiedCustomers()));

        while (true) {
            int choice = dispMenu(new String[]{"Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"});

            if (choice == 1) dispSummary();
            else if (choice == 2) {
                manageSort(Comparator
                        .comparing(Customer::getName)
                        .thenComparing(Customer::getUserId));
            } else if (choice == 3) {
                manageSort(Comparator
                        .comparing(Customer::getSpentTime)
                        .thenComparing(Customer::getName));
            } else if (choice == 4) {
                manageSort(Comparator
                        .comparing(Customer::getTotalPay)
                        .thenComparing(Customer::getName));
            } else if (choice == 5) return;
//            else System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }


    public void dispSummary() {
        if (classifiedCusGroup == null) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            return;
        }
        classifiedCusGroup.print();
    }

    public void manageSort(Comparator<Customer> comparator) {
        while (true) {
            String strOrder = chooseSortOrder();
            if (strOrder.equals(Message.END_MSG)) return;

            try {
                OrderType orderType = OrderType.valueOf(strOrder).replaceFullName();
                if (orderType == OrderType.ASCENDING) classifiedCusGroup.sort(comparator);
                else if (orderType == OrderType.DESCENDING) classifiedCusGroup.sort(comparator.reversed());
                else throw new InputRangeException();

            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);
            }

            dispSummary();
        }
    }

    public String chooseSortOrder() {
        while (true) {
            try {
                System.out.println("\n** Press 'end', if you want to exit! **");
                System.out.print("Which order (ASCENDING (A), DESCENDING (D))? ");
                String choice = nextLine(Message.END_MSG);

                if (choice.equals("")) throw new InputEmptyException();
                //if (choice.equals(Message.END_MSG)) return choice;

                try {
                    OrderType orderType = OrderType.valueOf(choice).replaceFullName();
                    for (int i = 0; i < OrderType.size(); i++) {
                        if (orderType == OrderType.values()[i]) {
                            return choice;
                        }
                    }
                    throw new InputRangeException();
                } catch (IllegalArgumentException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                }
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            }
        }
    }
}
