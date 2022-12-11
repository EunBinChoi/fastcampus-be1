package me.day25.smartstore.menu;

import me.day25.smartstore.customers.Customer;
import me.day25.smartstore.customers.Customers;
import me.day25.smartstore.exception.ArrayEmptyException;
import me.day25.smartstore.exception.InputEmptyException;
import me.day25.smartstore.exception.InputEndException;
import me.day25.smartstore.exception.InputRangeException;
import me.day25.smartstore.groups.Group;
import me.day25.smartstore.groups.Groups;
import me.day25.smartstore.util.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomerMenu extends Menu {

    /////////////////////////////////////////
    ////////////// singleton ////////////////
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }
    /////////////////////////////////////////
    /////////////////////////////////////////

    private Groups allGroups = Groups.getInstance();
    private Customers allCustomers = Customers.getInstance();

    public void manageCustomerMenu() {
        while (true) {
            int choice = dispMenu(
                    new String[]{"Set Customer Data", "View Customer Data",
                            "Update Customer Data", "Delete Customer Data", "Back"});

            if (choice == 1) {
                int size = 0;
                size = getCustomerSizeToAdd();
                setCustomerData(size);
            } else if (choice == 2) viewCustomerData();
            else if (choice == 3) updateCustomerData();
            else if (choice == 4) deleteCustomerData();
            else if (choice == 5) return;
//            else System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    public void setCustomerData(int size) {
        for (int i = 0; i < size; ++i) {
            Customer customer = new Customer();
            System.out.println("\n====== Customer " + (i + 1) + " Info. ======");

            while (true) {
                int choice = dispMenu(
                        new String[]{"Customer Name", "Customer ID",
                                "Customer Spent Time", "Customer Total Pay", "Back"});
                if (choice == 1) setCustomerName(customer);
                else if (choice == 2) setCustomerUserId(customer);
                else if (choice == 3) setCustomerSpentTime(customer);
                else if (choice == 4) setCustomerTotalPay(customer);
                else if (choice == 5) break;
//                else System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);

            }

            Group grp = allGroups.findGroupFor(customer);
            if (grp == null) customer.setGroup(null);
            else if (!grp.equals(customer.getGroup())) customer.setGroup(grp);

            allCustomers.add(customer);
        }
    }


    public void viewCustomerData() {
        if (allCustomers.size() == 0) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }

        System.out.println("\n======= Customer Info. =======");

        for (int i = 0; i < allCustomers.size(); ++i) {
            Customer cust = allCustomers.get(i);
            if (cust != null) System.out.println("No. " + (i + 1) + " => " + cust);
            else System.out.println("null");
        }

    }

    public void updateCustomerData() {
        viewCustomerData();
        int custNo = 0;

        try {
            custNo = findCustomer();
        } catch (ArrayEmptyException | InputEndException e) {
            return;
        }


        Customer customer = allCustomers.get(custNo - 1);
        if (customer == null) return;

        while (true) {
            int choice = dispMenu(
                    new String[]{"Customer Name", "Customer ID",
                            "Customer Spent Time", "Customer Total Pay", "Back"});

            if (choice == 1) setCustomerName(customer);
            else if (choice == 2) setCustomerUserId(customer);
            else if (choice == 3) setCustomerSpentTime(customer);
            else if (choice == 4) setCustomerTotalPay(customer);
            else if (choice == 5) break;
//            else System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);

        }

        Group grp = allGroups.findGroupFor(customer);
        if (grp == null) customer.setGroup(null);
        else if (!grp.equals(customer.getGroup())) customer.setGroup(grp);
    }

    public void deleteCustomerData() {
        viewCustomerData();
        int custNo = 0;

        try {
            custNo = findCustomer();
        } catch (ArrayEmptyException | InputEndException e) {
            return;
        }


        Customer customer = allCustomers.get(custNo - 1);
        System.out.println(customer);

        allCustomers.pop(custNo - 1);
        viewCustomerData();

    }

//    public int dispManageCustomerMenu() {
//        while (true) {
//            try {
//                System.out.println();
//                System.out.println("==============================");
//                System.out.println(" 1. Set Customer Data");
//                System.out.println(" 2. View Customer Data");
//                System.out.println(" 3. Update Customer Data");
//                System.out.println(" 4. Delete Customer Data");
//                System.out.println(" 5. Back");
//                System.out.println("==============================");
//                System.out.print("Choose One: ");
//                int choice = Integer.parseInt(Menu.nextLine());
//                if (choice >= 1 && choice <= 5) {
//                    return choice;
//                }
//                throw new InputRangeException();
//
//            } catch (NumberFormatException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
//            } catch (InputRangeException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
//            }
//        }
//    }


    private int getCustomerSizeToAdd() {
        while (true) {
            try {
                //System.out.println("\n** Press 'end', if you want to exit! **");
                System.out.print("How many customers to input? ");
                int size = Integer.parseInt(nextLine(Message.END_MSG));
                if (size < 0) throw new InputRangeException();
                return size;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return -1;
            }
        }
    }

//    public int dispSetCustomerMenu() {
//        while (true) {
//            try {
//                System.out.println();
//                System.out.println("==============================");
//                System.out.println(" 1. Customer Name");
//                System.out.println(" 2. Customer ID");
//                System.out.println(" 3. Customer Spent Time");
//                System.out.println(" 4. Customer Total Pay");
//                System.out.println(" 5. Back");
//                System.out.println("==============================");
//                System.out.print("Choose One: ");
//                int choice = Integer.parseInt(Menu.nextLine());
//                if (choice >= 1 && choice <= 5) {
//                    return choice;
//                }
//
//                throw new InputRangeException();
//            } catch (NumberFormatException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
//            } catch (InputRangeException e) {
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
//            }
//        }
//    }

    public void setCustomerName(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Name: ");
//                String REGEX = "^[a-zA-Z]{3,}$";
                String name = nextLine(Message.END_MSG);
                if (name == null || name.equals("")) throw new InputEmptyException();
                customer.setName(name);
                return;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            }
        }
    }

    public void setCustomerUserId(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's ID: ");
//                String REGEX = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
                String userId = nextLine(Message.END_MSG);
                if (userId == null || userId.equals("")) throw new InputEmptyException();
                customer.setUserId(userId);
                return;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            }
        }
    }

    public void setCustomerSpentTime(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Spent Time: ");
                int spentTime = Integer.parseInt(nextLine(Message.END_MSG));
                if (spentTime < 0) throw new InputRangeException();
                customer.setSpentTime(spentTime);
                return;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            }
        }
    }

    public void setCustomerTotalPay(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Total Payment: ");
                int totalPay = Integer.parseInt(nextLine(Message.END_MSG));
                if (totalPay < 0) throw new InputRangeException();
                customer.setTotalPay(totalPay);
                return;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            }
        }
    }

    // <<Reflection>> setCustomerName, setCustomerUserId, setCustomerSpentTime, setCustomerTotalPay
    public void setCustomerField(String message, Customer customer, Method method) {
        while (true) {
            try {
                System.out.print("\n" + message);
                int input = Integer.parseInt(nextLine(Message.END_MSG));
                if (input < 0) throw new InputRangeException();
                method.invoke(customer, input);
                return;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int findCustomer() throws ArrayEmptyException, InputEndException {
        int size = allCustomers.size();
        if (size == 0) throw new ArrayEmptyException();

        while (true) {
            try {
                System.out.print("\nWhich customer ( 1 ~ " + size + " )? ");
                int custNo = Integer.parseInt(nextLine());
                if (!(custNo >= 1 && custNo <= size)) throw new InputRangeException();
                return custNo;
            } catch (NumberFormatException e) {
                System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println("\n" + Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
