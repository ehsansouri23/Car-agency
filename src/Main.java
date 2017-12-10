/**
 * Created by ehsan on 12/10/2017.
 */
public class Main {
    public static void main(String[] args) {
        ServiceList serviceList = new ServiceList();
        Service s1 = new Service("1");
        Service s2 = new Service("2");
        Service s3 = new Service("3");
        serviceList.addService("1");
        serviceList.addService("2");
        serviceList.addService("3");
        serviceList.addService("4");
        serviceList.addSubService("22", "2");
        serviceList.addSubService("23", "2");
        serviceList.addSubService("24", "2");
        serviceList.addSubService("31", "4");

        serviceList.printall();
    }
}
