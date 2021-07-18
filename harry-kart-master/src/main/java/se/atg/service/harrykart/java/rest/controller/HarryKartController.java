package se.atg.service.harrykart.java.rest.controller;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.rest.model.inputRequest.HarryKart;
import se.atg.service.harrykart.java.rest.model.outputResponse.RaceResult;
import se.atg.service.harrykart.java.rest.service.FastestThreeHorse;
import se.atg.service.harrykart.java.rest.service.HostMatch;

@RestController
@RequestMapping("/java/api")
public class HarryKartController {
    private static final Logger logger = LoggerFactory.getLogger(HarryKartController.class);

    @Autowired
    private HostMatch fastestThreeHorse;

    @SneakyThrows
    @PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RaceResult> playHarryKart(@RequestBody HarryKart request) {
        RaceResult result = new RaceResult();
        try {
            result = (RaceResult) fastestThreeHorse.computeResult(request);
        } catch (Exception exception) {
            logger.error("Exception in HarryKartController with Post /java/api/play: " + exception.getMessage(), exception);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
