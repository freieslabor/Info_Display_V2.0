package Info_Display.V20.rest;

import Info_Display.V20.lib.Exception.SpaceInformationException.SpaceInformationException;
import Info_Display.V20.persistence.entity.SpaceInfromationEntity;
import Info_Display.V20.persistence.service.SpaceInformationService;
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
    public void createSpaceInformation(@RequestBody SpaceInfromationEntity entity) throws SpaceInformationException {
        service.createSpaceInformation(entity);
    }

    @GetMapping(value = "/SpaceInformation/ALl")
    public ResponseEntity<List<SpaceInfromationEntity>> getAllSpaceInformation(){
        return service.getAllSpaceInformation();
    }
}