/**
 * Created by ehsan on 12/10/2017.
 */
public class ServiceList {
    private Service head;

    public ServiceList() {
        this.head = null;
    }

    public Service getHead() {
        return head;
    }

    /**
     * this function will add a main service to out service list.
     * @param serviceName
     */
    public void addService(String serviceName) {
        Service service = new Service(serviceName);
        if (head == null)
            head = service;
        else {
            Service p = head;
            for (; p.next != null; p = p.next) ;
            p.next = service;
        }
    }

    public Service findService(String serviceName) {
        return findService(head, serviceName);
    }

    /**
     * this function will search for a service in service list and return it. if that does not exists, will return null.
     * searched service can also be a subservice
     * @param head
     * @param serviceName
     * @return
     */
    public Service findService(Service head, String serviceName) {
        Service p = head;
        if (p == null)
            return null;
//        System.out.println("////////////////////////");
//        testprint(head);
//        System.out.println("////////////////////////");
//        for (; p != null; p = p.next) {
//            System.out.println(p.getName() + "," + p.getTag() + " is checked");
//        if (p.getName().equals(serviceName)) {
//            return p;
//        } else {
//            if (p.getTag() == 1) {
//                Service v = findService(p.innerList, serviceName);
//                if (v == null && p.next != null)
//                    return findService(p.next, serviceName);
//            }
//            else if (p.next != null)
//                return findService(p.next, serviceName);
//
//        }


        while (p != null) {
            if (p.getName().equals(serviceName))
                return p;
            if (p.getTag() == 1) {
                if (findService(p.innerList, serviceName) != null)
                    return findService(p.innerList, serviceName);
            }
            p = p.next;
        }
        return null;
//        }


//         if(head.getName().equals(serviceName))
//             return head;
//         else if (head.getTag() == 1)
//             return findService(head.innerList, serviceName);
//         else return findService(head.next, serviceName);
//        return null;
    }

    /**
     * this function will add a subservice to out service list
     * @param subServiceName
     * @param serviceName
     */
    public void addSubService(String subServiceName, String serviceName) {
//        System.out.println("in add sub func ! ");
        Service subService = new Service(subServiceName);
        Service p = findService(head, serviceName);
        if (p == null) {
            System.out.println("Not such a service named " + serviceName);
            return;
        }
        p.setTag(1);
        Service q = p.innerList;
        if (q == null) {
            p.innerList = subService;
        } else {
            for (; q.next != null; q = q.next) /*System.out.println(q.getName() + " in add function");*/ ;
            q.next = subService;
        }
    }

    /**
     * this function will delete a service from out service list. this service can also be a sub service.
     * if we delete a main service, all its sub services will be deleted too.
     * @param serviceName
     */
    public void deleteService(String serviceName) {
        Service service = new Service(serviceName);
        if (head.next == null && head.getName().equals(serviceName)) {
            head = null;
            return;
        }
        if (head.next != null && head.getName().equals(serviceName)) {
            head = head.next;
            return;
        }
        Service p = head;
        Service q = head.next;
        for (; q != null && !q.getName().equals(serviceName); q = q.next, p = p.next) ;
        if (q.next == null) {
            p.next = null;
            return;
        }
        p.next = q.next;
    }

    /**
     * this function will print all services with their subservices.
     */
    public void printall() {
        printall(head, 1);
    }

    private void printall(Service head, int n) {
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
                printall(p.innerList, n + 1);
            }
        System.out.println("    ");
    }

    public void testprint(Service head) {
        Service p = head;
        for (; p != null; p = p.next)
            System.out.println(p.getName() + "," + p.getTag());
    }
}
