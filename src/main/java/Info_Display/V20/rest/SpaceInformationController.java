package Info_Display.V20.rest;

import Info_Display.V20.persistence.entity.SpaceInformationEntity;
import Info_Display.V20.persistence.service.SpaceInformationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpaceInformationController {

    @Autowired
    SpaceInformationService service;

    @PostMapping(value = "/SpaceInformation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSpaceInformation(@RequestBody SpaceInformationEntity entity) {
        service.createSpaceInformation(entity);
    }

    @GetMapping(value = "/SpaceInformation/All")
    public ResponseEntity<List<SpaceInformationEntity>> getAllSpaceInformation(){
        return new ResponseEntity<>(service.getAllSpaceInformation(), HttpStatus.OK);
    }

    @GetMapping(value = "/SpaceInformation{title}")
    public ResponseEntity<SpaceInformationEntity> getInformationByTitle(@RequestParam("title") String title) {
        return service.getInformationByTitle(title);
    }

    @PutMapping(value = "/SpaceInformation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSpaceInformation(@RequestBody SpaceInformationEntity entity) throws JsonProcessingException {
        service.updateInformation(entity);
    }
}