package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.CalenderException.CalenderEntryDeleteException;
import Info_Display.V20.lib.Exception.CalenderException.CalenderEntrySaveException;
import Info_Display.V20.lib.Exception.CalenderException.EntryExistsException;
import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceCalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class HackerspaceCalenderService {

    @Autowired
    HackerspaceCalenderRepository repo;

    Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    public ResponseEntity createCalenderEntry(HackerspaceCalenderEntity entry) throws EntryExistsException, CalenderEntrySaveException {
        if(repo.findByDateAndComment(entry.getDate(), entry.getComment()) == null){
            HackerspaceCalenderEntity entity = new HackerspaceCalenderEntity();
            entity.setName(entry.getName());
            entity.setComment(entry.getComment());
            entity.setDate(entry.getDate());
            repo.save(entity);
            if (repo.existsById(entity.getId()) == true){
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
        if(repo.existsById(entry.getId())){
            repo.deleteById(entry.getId());
            if(!repo.existsById(entry.getId())){
                log.warning("Request for deleted a Entry");
                return new ResponseEntity<String>("Deleted successful!", HttpStatus.OK);
            }else{
                throw new CalenderEntryDeleteException("Entry can\'t deleted");
            }
        }else{
            throw new EntryExistsException("Service can\'t find this entry");
        }
    }

    public ResponseEntity<String> updateCalenderEntry(HackerspaceCalenderEntity entry) throws EntryExistsException {
        if (repo.existsById(entry.getId())){
            HackerspaceCalenderEntity entity = repo.getOne(entry.getId());
            entity.setName(entry.getName());
            entity.setId(entity.getId());
            entity.setComment(entry.getComment());
            entity.setDate(entry.getDate());

            repo.save(entity);
            return new ResponseEntity<String>("Update successful!", HttpStatus.ACCEPTED);
        }else{
            throw new EntryExistsException("Entry with id: " + entry.getId() + " dosen \'t existed in the database");
        }

    }

    public List<HackerspaceCalenderEntity> getAllEntries(){ return repo.findAll();}

    public HackerspaceCalenderEntity getByDate(LocalDateTime date){ return repo.findByDate(date); }

    public HackerspaceCalenderEntity getByID(UUID id){ return repo.findById(id).get();}

    public List<HackerspaceCalenderEntity> getByDateContaining(String date){
        return repo.findByDateContaining(date);
    }

}
