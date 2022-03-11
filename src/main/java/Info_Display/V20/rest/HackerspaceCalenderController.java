package Info_Display.V20.rest;

import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.service.HackerspaceCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class HackerspaceCalenderController {

    @Autowired
    HackerspaceCalenderService service;

    @GetMapping(value = "/calender/All")
    public List<HackerspaceCalenderEntity> getCalender(){
        return service.getAllEntries();
    }

    @GetMapping(value = "/calender")
    public List<HackerspaceCalenderEntity> getCalenderByDate(){
        return service.getByDateContaining(LocalDate.now().toString());
    }

    @GetMapping(value = "/calender/{id}")
    public HackerspaceCalenderEntity getCalenderEntryByID(@PathVariable("id")UUID id){
        return service.getByID(id);
    }

    @PostMapping(value = "/calender")
    public ResponseEntity createCalenderEntry(@RequestBody HackerspaceCalenderEntity entity) {
        return service.createCalenderEntry(entity);
    }

    @PutMapping(value = "/calender")
    public ResponseEntity<String> updateCalenderEntry(@RequestBody HackerspaceCalenderEntity entry)  {
        return service.updateCalenderEntry(entry);
    }

    @DeleteMapping(value = "/calender/{id}")
    public ResponseEntity<String> deleteEntryByID(@PathVariable("id") UUID uuid) {
        return service.deleteCalenderEntry(uuid);
    }

}