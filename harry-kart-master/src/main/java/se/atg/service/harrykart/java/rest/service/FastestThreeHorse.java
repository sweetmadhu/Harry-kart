package se.atg.service.harrykart.java.rest.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kotlin.Pair;
import org.springframework.stereotype.Service;
import se.atg.service.harrykart.java.rest.model.inputRequest.HarryKart;
import se.atg.service.harrykart.java.rest.model.inputRequest.Loop;
import se.atg.service.harrykart.java.rest.model.inputRequest.Participant;
import se.atg.service.harrykart.java.rest.model.outputResponse.RaceResult;
import se.atg.service.harrykart.java.rest.model.outputResponse.Race;

@Service
public class FastestThreeHorse implements HostMatch<HarryKart, RaceResult> {

    @Override
    public RaceResult computeResult(HarryKart request) throws Exception {

        RaceResult result = new RaceResult();
        HashMap<Integer, String> laneParticipantNameMap = new LinkedHashMap<>();

        //step 1: Get the map of time taken in each lane, and lane to horse name
        Double distance = 1000D;
        HashMap<Integer, Pair<Double, Integer>> trackLaneAndTime = new HashMap<>();
        for (Participant participant : request.getParticipant()) {
            Double time = (distance / participant.getBaseSpeed());
            trackLaneAndTime.put(participant.getLane(), new Pair<>(time, participant.getBaseSpeed()));
            laneParticipantNameMap.put(participant.getLane(), participant.getName());
        }

        //step 2: Calculate the time taken in each lane

        if (laneParticipantNameMap.size() > 2) {
            for (int i = 0; i < request.getNumberOfLoops() - 1; i++) {
                Loop currentLoop = request.getLoop().get(i);
                currentLoop.getLane().forEach(lane -> {
                    int laneNumber = lane.getNumber();
                    Pair<Double, Integer> param = trackLaneAndTime.get(laneNumber);
                    double time = param.getFirst();
                    int speed = param.getSecond();
                    speed = speed + lane.getValue();
                    time = time + (distance / speed);
                    trackLaneAndTime.put(laneNumber, new Pair<>(time, speed));
                });
            }
            List list = new LinkedList<>(trackLaneAndTime.entrySet());

           // step 3: Sort the  map on the basis of time taken in each lane
            Collections.sort(list, new Comparator() {

                @Override
                public int compare(Object o1, Object o2) {
                    return ((Pair<Double, Integer>) (((Map.Entry) (o1)).getValue())).getFirst().compareTo(((Pair<Double, Integer>) (((Map.Entry) (o2)).getValue())).getFirst());
                }
            });

            //step 4: Get the first 3 lanes having least time and map back to horse name
            int resultCount = 1;
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                Map.Entry entry = (Map.Entry) it.next();
                Race resultObject = new Race();
                resultObject.setHorse(laneParticipantNameMap.get(entry.getKey()));
                resultObject.setPosition(resultCount);
                if (result.getRanking() == null) {
                    LinkedList<Race> race = new LinkedList();
                    race.add(resultObject);
                    result.setRanking(race);
                } else {
                    result.pushToRanking(resultObject);
                }
                if (resultCount >= 3)
                    break;
                resultCount++;
            }
        } else {
            throw new Exception("Cannot compute fastest three as less than 3 inputs are provided");
        }

        return result;

    }

}
