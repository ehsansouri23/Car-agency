import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ehsan on 12/10/2017.
 */
public class AgencyList {
    private Agency head;
    private ServiceList serviceList;

    public AgencyList(ServiceList serviceList) {
        head = null;
        this.serviceList = serviceList;
    }

    public Agency getHead() {
        return head;
    }

    /**
     * this function will add an agency to out agency list. o(n)
     * @param agencyName
     */
    public void addAgency(String agencyName) {
        Agency agency = new Agency(agencyName);
        System.out.println(agency + " / " + agency.getName() + " added");
        System.out.println("size: " + agency.orders.size());
        if (head == null)
            head = agency;
        else {
            Agency p = head;
            for (; p.next != null; p = p.next) ;
            p.next = agency;
        }
    }

    /**
     * this function will add an offer to offer list of an specific agency. o(n)
     * @param serviceName
     * @param agencyName
     */
    public void addOffer(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        for (; p != null && !p.getName().equals(serviceName); p = p.next) ;
        Agency q = head;
        for (; q != null && !q.getName().equals(agencyName); q = q.next) ;
        p.setNumberOfProviders(p.getNumberOfProviders() + 1);
        q.providedServices.add(p);
    }

    /**
     * this function will delete a service from provided services list of an specific agency. o(nlogn)
     * @param serviceName
     * @param agencyName
     */
    public void delete(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        for (; p != null && !p.getName().equals(serviceName); p = p.next) ;
        Agency q = head;
        for (; q != null && !q.getName().equals(agencyName); q = q.next) ;
        if (p.getNumberOfProviders() > 1) {
            p.setNumberOfProviders(p.getNumberOfProviders() - 1);
            return;
        } else {
            serviceList.deleteService(serviceName);
        }
    }

    public Agency findAgency(String agencyName) {
        return findAgency(head, agencyName);
    }

    /**
     * this function will find a specific agency in agency list and return it. if agency does not exists, will
     * return null.
     *
     * @param head
     * @param agencyName
     * @return
     */
    public Agency findAgency(Agency head, String agencyName) {
        Agency p = head;
        while (p != null) {
            if (p.getName().equals(agencyName))
                return p;
            p = p.next;
        }
        return null;
    }

    /**
     * this function will check if a service is in list of services that an agency provides. o(n)
     *
     * @param serviceName
     * @param agencyName
     * @return
     */
    public boolean serviceIsInAgency(String serviceName, String agencyName) {
        Agency agency = findAgency(agencyName);
        System.out.println(agency.getName() + " found");
        boolean found = false;
        for (int i = 0; i < agency.providedServices.size(); i++) {
            if (agency.providedServices.get(i).getName().equals(serviceName))
                found = true;
            else {
                if (serviceList.findService(agency.providedServices.get(i).innerList, serviceName) != null)
                    found = true;
            }
        }

        return found;
    }

    /**
     * this function will add an order to an specific agency's order list and make it to max heap again.
     * @param serviceName
     * @param agencyName
     * @param priority
     * @param customerName
     */
    public void order(String serviceName, String agencyName, int priority, String customerName) {
        Agency agency = findAgency(agencyName);
        if (agency == null) {
            System.out.println("Sorry. " + agencyName + " does not exists");
            return;
        }
        Order order = new Order(priority, customerName);
        if (serviceIsInAgency(serviceName, agencyName)) {
            agency.orders.add(order);
        } else {
            System.out.println("Sorry. " + agencyName + " does not provide this service");
            return;
        }

        toMaxHeapAfterAdding(agency.orders);
    }

    /**
     * this function will print all orders of an specific agency. o(n^2)
     * @param agencyName
     */
    public void printOrders(String agencyName) {
        Agency agency = findAgency(agencyName);
        for (int i = 0; i < agency.orders.size(); i++) {
            System.out.println(agency.orders.get(i).getCustomerName());
        }
        toMaxHeapAfterDeleting(agency.orders);
    }

    /**
     * this function will make order list a max heap after adding a new order. 0(nlogn)
     * @param orders
     */
    public void toMaxHeapAfterAdding(ArrayList<Order> orders) {
        if (orders.size() == 2)
            return;
        int root = (orders.size() - 1) / 2;
        while (root >= 1) {
            int rCh = root * 2 + 1;
            if (rCh >= orders.size())
                rCh = -1;
            int lCh = root * 2;
            if (rCh == -1) {
                if (orders.get(lCh).getPriority() > orders.get(root).getPriority()) {
                    Collections.swap(orders, lCh, root);
                }
            } else {
                int max = Math.max(orders.get(rCh).getPriority(), orders.get(lCh).getPriority());
                if (max > orders.get(root).getPriority()) {
                    if (orders.get(rCh).getPriority() == max)
                        Collections.swap(orders, rCh, root);
                    if (orders.get(lCh).getPriority() == max)
                        Collections.swap(orders, lCh, root);
                }
            }
            root /= 2;
        }
    }

    /**
     * this function will make order list a max heap after deleting its top element. 0(nlogn)
     * @param orders
     */
    public void toMaxHeapAfterDeleting(ArrayList<Order> orders) {
        if (orders.size() == 1)
            return;
        Collections.swap(orders, 1, orders.size() - 1);
        orders.remove(orders.size() - 1);
        int root = 1;
        while (root < orders.size() - 1) {
            int swapped = 0;
            int lCh = root * 2;
            int rCh = root * 2 + 1;
            if (rCh >= orders.size())
                rCh = -1;
            if (lCh >= orders.size())
                lCh = -1;
            if (rCh == -1 && lCh != -1) {
                if (orders.get(lCh).getPriority() > orders.get(root).getPriority()) {
                    Collections.swap(orders, lCh, root);
                    root = lCh;
                    swapped = 1;
                }
            }
            else if (lCh == -1 && rCh != -1) {
                if (orders.get(rCh).getPriority() > orders.get(root).getPriority()) {
                    Collections.swap(orders, rCh, root);
                    root = rCh;
                    swapped = 1;
                }
            }
            else if (rCh == -1 && lCh == -1)
                return;
            else {
                int max = Math.max(orders.get(rCh).getPriority(), orders.get(lCh).getPriority());
                if (max > orders.get(root).getPriority()) {
                    if (orders.get(rCh).getPriority() == max) {
                        Collections.swap(orders, rCh, root);
                        root = rCh;
                    }
                    if (orders.get(lCh).getPriority() == max) {
                        Collections.swap(orders, lCh, root);
                        root = lCh;
                    }
                }
                swapped = 1;
            }
            if (swapped == 0)
                return;

        }
    }

    /**
     * this function will print all agencies. 0(n)
     */
    public void printAll() {
        Agency p = head;
        for (; p != null; p = p.next)
            System.out.println(p.getName());
    }
}
