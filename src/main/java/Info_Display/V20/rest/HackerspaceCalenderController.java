package Info_Display.V20.rest;

import Info_Display.V20.lib.Exception.CalenderException.CalenderEntrySaveException;
import Info_Display.V20.lib.Exception.CalenderException.EntryExistsException;
import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.service.HackerspaceCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return service.getByDateContaining(LocalDate.now().toString());
    }

    @PostMapping(value = "/calender")
    public ResponseEntity createCalenderEntry(@RequestBody HackerspaceCalenderEntity entity) throws CalenderEntrySaveException, EntryExistsException {
        return service.createCalenderEntry(entity);
    }

}