import javax.xml.bind.annotation.*;

/**
 * Created by vladimir on 13.12.15.
 */
@XmlRootElement
@XmlType
public class Node {

    private  String name;

    private long id;

    public Node() {}

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
