package jaxb.general;

import javassist.CannotCompileException;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {

    @XmlTransient
    public static final String XML_NAME = "node";

    @XmlTransient
    private Long instanceID;

    @XmlTransient
    private Process process;

    @XmlElement
    @XmlID
    private String id;

    @XmlElementWrapper
    @XmlElement(name = "output")
    @XmlIDREF
    private List<Node> outputs = new ArrayList<>();

    public void setInstanceID(Long instanceID) {
        this.instanceID = instanceID;
    }

    public void setID(String id) {
        this.id = id;
    }

    public List<Node> getOutputs() {
        return outputs;
    }

    public Process getProcess() {
        return process;
    }

    public Long getInstanceID() {
        return instanceID;
    }

    private void start() {
        process.addActiveNode(Node.this);
    }

    private void end() {
        process.removeActiveNode(Node.this);
        for (Node out : getOutputs()) {
            out.start();
        }
    }

    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller u, Object parent) {
        this.process = (Process) parent;
    }

    /* public void run() {

        // TODO VB: replace to tread pool.
        new Thread() {
            @Override
            public void run() {

                boolean continuation = false;
                try {
                    continuation = body();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                process.removeActiveNode(Node.this);
                if (continuation) {
                    for (Node out : getOutputs()) {
                        out.run();
                    }
                }
            }
        }.start();

    } */
}
