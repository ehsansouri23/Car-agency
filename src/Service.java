/**
 * Created by ehsan on 12/10/2017.
 */
public class Service {
    private String name;
    private int tag; // 1 for inner linked list, 0 for data
    Service next;
    Service innerList;

    public Service(String name) {
        this.name = name;
        this.tag = 0;
        this.next = null;
        this.innerList = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
