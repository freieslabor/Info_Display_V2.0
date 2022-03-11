package Info_Display.V20.persistence.service;

import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceCalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class HackerspaceCalenderService {

    @Autowired
    HackerspaceCalenderRepository repo;

    Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    public ResponseEntity createCalenderEntry(HackerspaceCalenderEntity entry){
        if(repo.findByDateAndName(entry.getDate(), entry.getName()) == null){
            HackerspaceCalenderEntity entity = new HackerspaceCalenderEntity();
            entity.setName(entry.getName());
            entity.setComment(entry.getComment());
            entity.setDate(entry.getDate());
            repo.save(entity);
            if (repo.existsById(entity.getId()) == true){
                log.info("Created new Entry");
                return new ResponseEntity("New Entry saved!", HttpStatus.CREATED);
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Entry can\'t saved!");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Entry exisits in Database");
        }

    }

    public ResponseEntity deleteCalenderEntry(UUID id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            if(!repo.existsById(id)){
                return new ResponseEntity<>("Deleted successful!", HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Entry can\'t deleted");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calender entry with id: " + id + " dosen\'t exisits");
        }
    }

    public ResponseEntity<String> updateCalenderEntry(HackerspaceCalenderEntity entry){
        if (repo.existsById(entry.getId())){
            HackerspaceCalenderEntity entity = repo.getOne(entry.getId());
            entity.setName(entry.getName());
            entity.setId(entity.getId());
            entity.setComment(entry.getComment());
            entity.setDate(entry.getDate());

            repo.save(entity);
            return new ResponseEntity<String>("Update successful!", HttpStatus.ACCEPTED);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry with id: " + entry.getId() + " dosen\'t existed in the database");
        }

    }

    public List<HackerspaceCalenderEntity> getAllEntries(){ return repo.findAll();}

    public HackerspaceCalenderEntity getByDate(LocalDateTime date){ return repo.findByDate(date); }

    public HackerspaceCalenderEntity getByID(UUID id){ return repo.findById(id).get();}

    public List<HackerspaceCalenderEntity> getByDateContaining(String date){
        return repo.findByDateContaining(date);
    }

}
