/**
 * Created by ehsan on 12/13/2017.
 */
public class Order {
    private int priority;
    private long time;
    private String customerName;

    public Order(int priority, String customerName) {
        this.priority = priority;
        time = System.currentTimeMillis();
        this.customerName = customerName;
    }

    public int getPriority() {
        return priority;
    }

    public long getTime() {
        return time;
    }

    public String getCustomerName() {
        return customerName;
    }
}
