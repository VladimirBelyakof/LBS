package jaxb.general;

import javassist.CannotCompileException;
import reflection.ProcessCodeBuilder;

import javax.xml.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Out extends Node {

    @XmlElement
    private String condition;

    public boolean body() throws NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, InvocationTargetException, ClassNotFoundException {
        return ProcessCodeBuilder.eval(getProcess().getID(), getInstanceID(), condition, getProcess().getVariables(), boolean.class);
    }
}
