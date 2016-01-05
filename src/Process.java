import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vladimir on 13.12.15.
 */
@XmlRootElement
public class Process {

    @XmlElement
    private  String name;

    @XmlElement
    private long id;

    @XmlElementWrapper
    @XmlElement(name = "node")
    private List<Node> nodes = new ArrayList<>();

    @XmlElementWrapper
    @XmlElement(name = "variable")
    private List<Variable> variables = new ArrayList<>();

    public Process() {}

    Process(String name, long id) {
        this.id = id;
        this.name = name;
        Comparator<TimerNode> dateComparator = new Comparator<TimerNode>() {
            @Override
            public int compare(TimerNode firstTimerNode, TimerNode secondTimerNode) {
                return firstTimerNode.getRunDate().compareTo(secondTimerNode.getRunDate());
            }
        };
    }

    public static void main(String... arg) throws JAXBException {
        Process proc = new Process("MyProc", 1l);

        Node node1 = new Node();
        node1.setId(1l);
        node1.setName("nodeOne");
        proc.nodes.add(node1);

        Variable var = new Variable();
        var.setName("kj");
        proc.variables.add(var);

        StringWriter sw = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(Process.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(proc, sw);

        System.out.print(sw);
    }
}
