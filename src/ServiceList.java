/**
 * Created by ehsan on 12/10/2017.
 */
public class ServiceList {
    private Service head;

    public ServiceList() {
        this.head = null;
    }

    public void addService(String serviceName) {
        Service service = new Service(serviceName);
        if (head == null)
            head = service;
        else {
            Service p = head;
            for (; p.next != null; p = p.next);
            p.next = service;
        }
    }

//    public void addService(Service root, Service service) {
//        if (root == null)
//            root = service;
//        else {
//            Service p = root;
//            for (; p.next != null; p = p.next);
//            p.next = service;
//        }
//    }

    public void addSubService(String subServiceName, String serviceName) {
        Service subService = new Service(subServiceName);
        Service p = head;
        for (; p.next != null; p = p.next) {
            if (p.getName().equals(serviceName)) {
                p.setTag(1);
                Service q = p.innerList;
                if (q == null){
                    q = subService;
                }
                else {
                    for (; q.next != null; q = q.next);
                    q.next = subService;
                }
            }
        }
    }

    void printall() {
        Service p = head;
        for (; p != null; p = p.next)
            System.out.println(p.getName() + "," + p.getTag());
    }
}
