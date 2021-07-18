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
    public static void main(String ... args) throws JAXBException {

        SpringApplication.run(HarryKartApp.class, args);
        List<Participant> participantList = new LinkedList<Participant>();
        Participant participant = new Participant();
        participant.setLane(1);
        participant.setBaseSpeed(123);
        participant.setName("test");
        participantList.add(participant);
        HarryKart kart = new HarryKart();
        kart.setNumberOfLoops(1);
        kart.setParticipant(participantList);
        Loop loop = new Loop();
        List<Loop> loopList = new LinkedList<>();
        List<Lane> list = new LinkedList<>();
        Lane lane = new Lane();
        lane.setValue(1);
        lane.setNumber(-1);

        Lane lane2 = new Lane();
        lane.setValue(2);
        lane.setNumber(-2);

        list.add(lane);
        list.add(lane2);

        loop.setLane(list);
        loop.setNumber(1);
        loopList.add(loop);
        kart.setLoop(loopList);
        JAXBContext context = JAXBContext.newInstance(HarryKart.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(kart,System.out);
    }
}
