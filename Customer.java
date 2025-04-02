

public class Customer {
    protected String phoneNumber;
    protected String userName;
    protected String pizzaName;
    protected int quantity;
    protected double bill;

    public Customer(String phoneNumber, String name, String pizzaName, int quantity, double bill) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.pizzaName = pizzaName;
        this.quantity = quantity;
        this.bill = bill;
    }
}
