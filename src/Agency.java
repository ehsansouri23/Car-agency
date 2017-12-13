import java.util.ArrayList;

/**
 * Created by ehsan on 12/10/2017.
 */
public class Agency {
    private String name;
    ArrayList<Service> providedServices;
    Agency next;

    public Agency(String name) {
        this.name = name;
        this.providedServices = new ArrayList<>();
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
