package DBUtils;

public class Order {
    private int orderId;
    private int quantity;
    private String userName;
    private int productId;

    public Order() {
    }

    public Order(int quantity, String userName, int productId) {
        this.quantity = quantity;
        this.userName = userName;
        this.productId = productId;
    }

    public Order(int orderId, int quantity, String userName, int productId) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.userName = userName;
        this.productId = productId;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
