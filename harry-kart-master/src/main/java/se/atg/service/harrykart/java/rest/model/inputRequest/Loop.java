package se.atg.service.harrykart.java.rest.model.inputRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import se.atg.service.harrykart.java.rest.model.inputRequest.Lane;

@XmlRootElement(name = "loop")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Loop {
    @XmlElement(name = "lane")
    private List<Lane> lane;

    @XmlAttribute(name="number")
    private int number;
}
