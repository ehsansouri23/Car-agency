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

    public void addAgency(String agencyName) {
        Agency agency = new Agency(agencyName);
        if (head == null)
            head = agency;
        else {
            Agency p = head;
            for (; p.next != null; p = p.next) ;
            p.next = agency;
        }
    }

    public void addOffer(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        for (; p != null && !p.getName().equals(serviceName); p = p.next) ;
        Agency q = head;
        for (; q != null && !q.getName().equals(agencyName); q = q.next) ;
        p.setNumberOfProviders(p.getNumberOfProviders() + 1);
        q.providedServices.add(p);
    }

    public void delete(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        Service beforeP = serviceList.getHead();
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
     * this function will check if a service is in list of services that an agency provides.
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

    public void order(String serviceName, String agencyName, int priority) {
        Agency agency = findAgency(agencyName);
        if (agency != null)
            System.out.println(agency.getName() + " found");
        for (int i = 0; i < agency.providedServices.size(); i++) {
            if (agency.providedServices.get(i).getName().equals(serviceName)) {
                System.out.println(serviceName + " ordered succesfully");
//                System.out.println("Not such a service you want to order named " + serviceName);
                return;
            } else if (serviceList.findService(agency.providedServices.get(i).innerList, serviceName) == null) {
                System.out.println("Not such a service you want to order named " + serviceName);
                return;
            } else System.out.println(serviceName + " ordered succesfully");

        }
//        System.out.println(serviceName + " ordered succesfully");
    }

    public void printAll() {
        Agency p = head;
        for (; p != null; p = p.next)
            System.out.println(p.getName());
    }
}
