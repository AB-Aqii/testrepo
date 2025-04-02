

public class HomeDeliveryCustomer extends Customer {
    private String address;
    private double deliveryCharges;

    public HomeDeliveryCustomer(String phoneNumber, String name, String pizzaName, int quantity, double bill, String address, double deliveryCharges) {
        super(phoneNumber, name, pizzaName, quantity, bill);
        this.address = address;
        this.deliveryCharges = deliveryCharges;
    }

    public String getAddress() {
        return address;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }
}
