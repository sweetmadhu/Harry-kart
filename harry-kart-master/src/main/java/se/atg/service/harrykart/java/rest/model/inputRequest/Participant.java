package se.atg.service.harrykart.java.rest.model.inputRequest;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="participant")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@XmlType(propOrder = {
        "lane",
        "name",
        "baseSpeed"
})
public class Participant {
    private Integer lane;
    private  String name;
    private Integer baseSpeed;

}
