import java.util.Scanner;

/**
 * Created by ehsan on 12/10/2017.
 */
public class Main {
    int state = 0;

    public static void main(String[] args) {
        ServiceList serviceList = new ServiceList();
        AgencyList agencyList = new AgencyList(serviceList);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println(input);
            String inputs[] = input.split(" ");
            if (inputs[0].equals("add")) {
                if (inputs[1].equals("service"))
                    serviceList.addService(inputs[2]);
                if (inputs[1].equals("subservice"))
                    serviceList.addSubService(inputs[2], inputs[4]);
                if (inputs[1].equals("offer"))
                    agencyList.addOffer(inputs[2], inputs[4]);
                if (inputs[1].equals("agency"))
                    agencyList.addAgency(inputs[2]);
            }

            if (inputs[0].equals("delete"))
                agencyList.delete(inputs[1], inputs[3]);

            if (inputs[0].equals("list")) {
                if (inputs[1].equals("agencies"))
                    agencyList.printAll();
                if (inputs[1].equals("services") && inputs.length == 2)
                    serviceList.printall();
                if (inputs[1].equals("services") && inputs.length == 4)
                    serviceList.printall(serviceList.findService(inputs[3]).innerList, 1);
                if (inputs[1].equals("orders"))
                    agencyList.printOrders(inputs[2]);
            }

            if (inputs[0].equals("order")) {
                agencyList.order(inputs[1], inputs[3], Integer.parseInt(inputs[7]), inputs[5]);
            }

        }

    }


}
