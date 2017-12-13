import java.util.ArrayList;

/**
 * Created by ehsan on 12/10/2017.
 */
public class Agency {
    private String name;
    ArrayList<Service> providedServices;
    ArrayList<Service> orders;
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

    public void printServices() {
        for (int i = 0; i < providedServices.size(); i++) {
            System.out.println("Service: " + providedServices.get(i).getName());
            printServices(providedServices.get(i).innerList, 1);
            System.out.println("----------------------------------------");
        }
    }

    private void printServices(Service head, int n) {
        Service p = head;
        for (; p != null; p = p.next)
            if (p.getTag() == 0) {
                System.out.print(p.getName());
                if (p.next != null)
                    System.out.print(",");
            } else {
                System.out.print(p.getName() + ",");
                System.out.println();
                for (int i = 0; i < n; i++) {
                    System.out.print("      ");
                }
                printServices(p.innerList, n + 1);
            }
        System.out.println("    ");
    }

}
