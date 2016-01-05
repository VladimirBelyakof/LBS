import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by vladimir on 13.12.15.
 */
@XmlRootElement
@XmlType
public class Variable {

    private String name;

    private String serializedValue;

    public Variable() {
    }

    public String getName() {
        return name;
    }

    public Serializable getValue() {
        return serializedValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.serializedValue = value;
    }
}
