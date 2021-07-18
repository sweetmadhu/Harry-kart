package se.atg.service.harrykart.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.atg.service.harrykart.java.rest.model.inputRequest.HarryKart;
import se.atg.service.harrykart.java.rest.model.inputRequest.Lane;
import se.atg.service.harrykart.java.rest.model.inputRequest.Loop;
import se.atg.service.harrykart.java.rest.model.inputRequest.Participant;

@SpringBootApplication
public class HarryKartApp {
    public static void main(String ... args){
        SpringApplication.run(HarryKartApp.class, args);
    }
}
