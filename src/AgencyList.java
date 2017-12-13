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

    public void addAgency(String agencyName) {
        Agency agency = new Agency(agencyName);
        if (head == null)
            head = agency;
        else {
            Agency p = head;
            for (; p.next != null; p = p.next);
            p.next = agency;
        }
    }

    public void addOffer(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        for (; p != null && !p.getName().equals(serviceName); p = p.next);
        Agency q = head;
        for (; q != null && !q.getName().equals(agencyName); q = q.next);
        p.setNumberOfProviders(p.getNumberOfProviders() + 1);
        q.providedServices.add(p);
    }

    public void delete(String serviceName, String agencyName) {
        Service p = serviceList.getHead();
        Service beforeP = serviceList.getHead();
        for (; p != null && !p.getName().equals(serviceName); p = p.next) ;
        Agency q = head;
        for (; q != null && !q.getName().equals(agencyName); q = q.next);
        if (p.getNumberOfProviders() > 1) {
            p.setNumberOfProviders(p.getNumberOfProviders() - 1);
            return;
        }
        else {
            serviceList.deleteService(serviceName);
        }
    }

    public void printAll(){
        Agency p = head;
        for (; p != null; p = p.next)
            System.out.println(p.getName());
    }
}
