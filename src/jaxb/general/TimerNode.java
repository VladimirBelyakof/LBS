package jaxb.general;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TimerNode extends Node {

    private Date runDate;

    public Date getRunDate() {
        return runDate;
    }
}
