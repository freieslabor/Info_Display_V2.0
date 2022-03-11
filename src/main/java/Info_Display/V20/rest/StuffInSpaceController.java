package Info_Display.V20.rest;

import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import Info_Display.V20.persistence.service.StuffInSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StuffInSpaceController {

    @Autowired
    StuffInSpaceService service;

    @GetMapping(value = "/StuffInSpace/All")
    public ResponseEntity<List<StuffInSpaceEntity>> getAllEntrys(){
        return service.getAllEntrys();
    }

    @PostMapping(value = "/StuffInSpace")
    public ResponseEntity<String> createStuffInSpaceEntry(@RequestBody StuffInSpaceEntity entity) {
        return service.createNewEntry(entity);
    }

    @GetMapping(value = "/StuffInSpace/Find{name}")
    public ResponseEntity<List<StuffInSpaceEntity>> getEntriesByContaining(@RequestParam("name") String name){
        return service.getEntryByContaining(name);
    }

    @PutMapping(value = "/StuffInSpace")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEntry(@RequestBody StuffInSpaceEntity entity) {
        service.updateEntry(entity);
    }

    @DeleteMapping(value = "/StuffInSpace{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEntry(@RequestParam("id") UUID id) {
        service.deleteEntryByUUID(id);
    }

}
