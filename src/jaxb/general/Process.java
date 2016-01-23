package jaxb.general;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Process {

    @XmlElement
    @XmlID
    private String id;

    @XmlElementWrapper
    @XmlElement(name = Node.XML_NAME)
    private List<Node> nodes = new ArrayList<>();

    @XmlTransient
    private Long instanceID;

    @XmlTransient
    private List<Node> activeNodes = new ArrayList<>();

    @XmlTransient
    private Map<String, Serializable> variables;

    public Process() {
        Comparator<TimerNode> dateComparator = new Comparator<TimerNode>() {
            @Override
            public int compare(TimerNode firstTimerNode, TimerNode secondTimerNode) {
                return firstTimerNode.getRunDate().compareTo(secondTimerNode.getRunDate());
            }
        };
    }

    public void init(Long instanceID, Map<String, Serializable> variables) {

        if (instanceID == null || variables == null) {
            throw new IllegalArgumentException();
        }
        this.instanceID = instanceID;
        this.variables = variables;
    }

    public void init(Long instanceID) {
        init(instanceID, new HashMap<String, Serializable>());
    }

    public String getID() {
        return id;
    }

    public Map<String, Serializable> getVariables() {
        return variables;
    }

    public boolean removeActiveNode(Node node) {
        synchronized (activeNodes) {
            return activeNodes.remove(node);
        }
    }

    public boolean addActiveNode(Node node) {
        synchronized (activeNodes) {
            return activeNodes.add(node);
        }
    }

    public boolean addActiveNodes(List<Node> nodes) {
        synchronized (activeNodes) {
            return activeNodes.addAll(nodes);
        }
    }

    public static void main(String... arg) throws JAXBException, InterruptedException {
 /*       Process proc = new Process();
        proc.init(787l);
        Out out = new Out();
        out.setInstanceID(67l);
        out.setID("out_1");

        Node node1 = new Node();
        node1.setInstanceID(199l);
        node1.setID("node_1");
        node1.getOutputs().add(out);
        proc.nodes.add(node1);


        StringWriter sw = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(Process.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(proc, sw);

        StringReader sr = new StringReader(sw.toString());
        Process prc = (Process) jc.createUnmarshaller().unmarshal(sr);

        System.out.println(sw);*/

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {


                System.out.println("------START--");

                try {
    //Thread.sleep(1999l);
                    Date ff = new Date();
                    while (new Date().getTime() < (ff.getTime() + 2000l)) {
                        Math.sin(33);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    System.out.println("----END----");
                }


                System.out.println("----END--1--");

            }
        });

    th.start();

        Thread.sleep(199l);
        //th.interrupt();
        th.stop();
    }
}
