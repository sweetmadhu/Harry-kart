package se.atg.service.harrykart.java.rest.model.outputResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceResult {
    List<Race> ranking;

    public void pushToRanking(Race result) {
        this.ranking.add(result);
    }
}
