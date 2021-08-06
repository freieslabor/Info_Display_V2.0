package Info_Display.V20.rest;

import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.service.HackerspaceCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

public class HackerspaceCalenderController {

    @Autowired
    HackerspaceCalenderService service;

    @GetMapping(path = "/calender")
    public List<HackerspaceCalenderEntity> getCalender(){
        return service.getAllEntries();
    }

    @GetMapping(path = "/calender{date}")
    public HackerspaceCalenderEntity getCalenderByDate(LocalDateTime date){
        return service.getByDate(date);
    }

}