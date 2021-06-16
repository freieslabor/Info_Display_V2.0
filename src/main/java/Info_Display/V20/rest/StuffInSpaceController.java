package Info_Display.V20.rest;

import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.CreateStuffInSpaceEntryException;
import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import Info_Display.V20.persistence.service.StuffInSpaceService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class StuffInSpaceController {

    @Autowired
    StuffInSpaceService service;

    @GetMapping(value = "/StuffInSpace/All")
    public ResponseEntity<List<StuffInSpaceEntity>> getAllEntrys(){
        return service.getAllEntrys();
    }

    @PostMapping(value = "/StuffInSpace{name,info,postion}")
    public ResponseEntity<StuffInSpaceEntity> createStuffInSpaceEntry(@RequestParam("name") String name, @RequestParam("info") String info, @RequestParam("position") String position) throws CreateStuffInSpaceEntryException {
        return service.createNewEntry(name, info, position);
    }

    @PostMapping(value = "/StuffInSpace/Find{name}")
    public ResponseEntity<List<StuffInSpaceEntity>> getEntriesByContaining(@RequestParam("name") String name){
        return service.getEntryByContaining(name);
    }

}
