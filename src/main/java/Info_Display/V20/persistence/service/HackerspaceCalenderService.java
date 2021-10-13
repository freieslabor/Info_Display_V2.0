package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.CalenderException.CalenderEntryDeleteException;
import Info_Display.V20.lib.Exception.CalenderException.CalenderEntrySaveException;
import Info_Display.V20.lib.Exception.CalenderException.EntryExistsException;
import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceCalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class HackerspaceCalenderService {

    @Autowired
    HackerspaceCalenderRepository repo;

    Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    public ResponseEntity createCalenderEntry(HackerspaceCalenderEntity entry) throws EntryExistsException, CalenderEntrySaveException {
        if(repo.findByDateAndComment(entry.getDate(), entry.getComment()) == null){
            HackerspaceCalenderEntity entity = new HackerspaceCalenderEntity();
            entity.setComment(entry.getComment());
            entity.setDate(entry.getDate());
            repo.save(entity);
            if (repo.existsById(entity.getUuid()) == true){
                log.info("Created new Entry");
                return new ResponseEntity("New Entry saved!", HttpStatus.CREATED);
            }else{
                throw new CalenderEntrySaveException("Entry can\'t saved!");
            }
        }else{
            throw new EntryExistsException("Entry exisits in Database");
        }

    }

    public ResponseEntity<String> deleteCalenderEntry(HackerspaceCalenderEntity entry) throws CalenderEntryDeleteException, EntryExistsException {
        if(repo.existsById(entry.getUuid())){
            repo.deleteById(entry.getUuid());
            if(!repo.existsById(entry.getUuid())){
                log.warning("Request for deleted a Entry");
                return new ResponseEntity<String>("Deleted successful!", HttpStatus.OK);
            }else{
                throw new CalenderEntryDeleteException("Entry can\'t deleted");
            }
        }else{
            throw new EntryExistsException("Service can\'t find this entry");
        }
    }

    public List<HackerspaceCalenderEntity> getAllEntries(){ return repo.findAll();}

    public HackerspaceCalenderEntity getByDate(LocalDateTime date){ return repo.findByDate(date); }

    public List<HackerspaceCalenderEntity> getByDateContaining(String date){
        return repo.findByDateContaining(date);
    }

}
