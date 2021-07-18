package se.atg.service.harrykart.java.rest.model.inputRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="harryKart")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class HarryKart {
    private int numberOfLoops;

    @XmlElementWrapper(name = "startList")
    private List<Participant> participant;

    @XmlElementWrapper(name = "powerUps")
    private List<Loop> loop;

}
