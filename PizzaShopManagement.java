

import java.io.*;
import java.util.*;
import javax.swing.*;

public class PizzaShopManagement {
    static Queue<TakeAwayCustomer> takeAwayQueue = new LinkedList<>();
    static Queue<DineInCustomer> dineInQueue = new LinkedList<>();
    static Stack<HomeDeliveryCustomer> homeDeliveryStack = new Stack<>();

    static List<String> menu = Arrays.asList("Chicken Tikka", "Arabic Ranch", "Chicken Fajita", "Cheese Lover", "Chicken Supreme");
    static List<Integer> prices = Arrays.asList(2000, 2500, 2400, 2200, 2700);

    // GUI Components
    private static JTextArea displayArea = new JTextArea(20, 50);
    private static JComboBox<String> pizzaComboBox = new JComboBox<>(menu.toArray(new String[0]));
    private static JTextField nameField = new JTextField(20);
    private static JTextField phoneField = new JTextField(15);  // Phone Number Field
    private static JTextField quantityField = new JTextField(5);
    private static JTextField addressField = new JTextField(30);
    private static JTextField distanceField = new JTextField(5);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pizza Shop Management System");
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Pizza:"));
        panel.add(pizzaComboBox);
        panel.add(new JLabel("Delivery Address (for Home Delivery):"));
        panel.add(addressField);
        panel.add(new JLabel("Distance (km, for Home Delivery):"));
        panel.add(distanceField);
        panel.add(new JScrollPane(displayArea));

        JButton placeTakeAwayOrderButton = new JButton("Place Take-Away Order");
        placeTakeAwayOrderButton.addActionListener(e -> placeTakeAwayOrder());
        panel.add(placeTakeAwayOrderButton);

        JButton placeDineInOrderButton = new JButton("Place Dine-In Order");
        placeDineInOrderButton.addActionListener(e -> placeDineInOrder());
        panel.add(placeDineInOrderButton);

        JButton placeHomeDeliveryOrderButton = new JButton("Place Home Delivery Order");
        placeHomeDeliveryOrderButton.addActionListener(e -> placeHomeDeliveryOrder());
        panel.add(placeHomeDeliveryOrderButton);

        JButton serveOrdersButton = new JButton("Serve Orders");
        serveOrdersButton.addActionListener(e -> serveOrders());
        panel.add(serveOrdersButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    static void placeTakeAwayOrder() {
        String name = nameField.getText();
        String phoneNumber = phoneField.getText();
        int pizzaChoice = pizzaComboBox.getSelectedIndex();
        int quantity = Integer.parseInt(quantityField.getText());

        double bill = prices.get(pizzaChoice) * quantity;
        takeAwayQueue.add(new TakeAwayCustomer(phoneNumber, name, menu.get(pizzaChoice), quantity, bill));
        displayArea.append("Take-Away order placed successfully!\n");
        clearFields();
    }

    static void placeDineInOrder() {
        String name = nameField.getText();
        String phoneNumber = phoneField.getText();
        int pizzaChoice = pizzaComboBox.getSelectedIndex();
        int quantity = Integer.parseInt(quantityField.getText());

        double bill = prices.get(pizzaChoice) * quantity;
        dineInQueue.add(new DineInCustomer(phoneNumber, name, menu.get(pizzaChoice), quantity, bill));
        displayArea.append("Dine-In order placed successfully!\n");
        clearFields();
    }

    static void placeHomeDeliveryOrder() {
        String name = nameField.getText();
        String phoneNumber = phoneField.getText();
        int pizzaChoice = pizzaComboBox.getSelectedIndex();
        int quantity = Integer.parseInt(quantityField.getText());
        String address = addressField.getText();
        int distance = Integer.parseInt(distanceField.getText());

        double deliveryCharges = distance * 50;
        double bill = prices.get(pizzaChoice) * quantity + deliveryCharges;
        homeDeliveryStack.push(new HomeDeliveryCustomer(phoneNumber, name, menu.get(pizzaChoice), quantity, bill, address, deliveryCharges));
        displayArea.append("Home Delivery order placed successfully!\n");
        clearFields();
    }

    static void serveOrders() {
        if (!takeAwayQueue.isEmpty()) {
            TakeAwayCustomer customer = takeAwayQueue.poll();
            displayArea.append("Serving Take-Away Customer: " + customer.name + " (Phone: " + customer.phoneNumber + ")\n");
        } else {
            displayArea.append("No Take-Away customers to serve.\n");
        }

        if (!dineInQueue.isEmpty()) {
            DineInCustomer customer = dineInQueue.poll();
            displayArea.append("Serving Dine-In Customer: " + customer.name + " (Phone: " + customer.phoneNumber + ")\n");
        } else {
            displayArea.append("No Dine-In customers to serve.\n");
        }

        if (!homeDeliveryStack.isEmpty()) {
            HomeDeliveryCustomer customer = homeDeliveryStack.pop();
            displayArea.append("Serving Home Delivery Customer: " + customer.name + " (Phone: " + customer.phoneNumber + ", Address: " + customer.getAddress() + ")\n");
        } else {
            displayArea.append("No Home Delivery customers to serve.\n");
        }
    }

    static void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        quantityField.setText("");
        addressField.setText("");
        distanceField.setText("");
    }
}
