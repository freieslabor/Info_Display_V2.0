package Info_Display.V20.rest;

import Info_Display.V20.lib.Exception.CalenderException.CalenderEntrySaveException;
import Info_Display.V20.lib.Exception.CalenderException.EntryExistsException;
import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.service.HackerspaceCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        return service.getByDateContaining(date);
    }

    @PostMapping(value = "/calender{comment, date}")
    public ResponseEntity createCalenderEntry(@RequestParam("comment") String comment, @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime date) throws CalenderEntrySaveException, EntryExistsException {
        return service.createCalenderEntry(comment, date);
    }

}