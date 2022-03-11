package Info_Display.V20.persistence.service;

import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import Info_Display.V20.persistence.repositroy.StuffInSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class StuffInSpaceService {

    @Autowired
    StuffInSpaceRepository repo;

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    public ResponseEntity<String> createNewEntry(StuffInSpaceEntity entity) {
        StuffInSpaceEntity entry = new StuffInSpaceEntity();
        entry.setName(entity.getName());
        entry.setInfo(entity.getInfo());
        entry.setPosition(entity.getPosition());

        repo.save(entry);

        return checkNewEntry(entry);

    }

    public ResponseEntity<List<StuffInSpaceEntity>> getEntryByContaining(String name){ return new ResponseEntity<List<StuffInSpaceEntity>>(repo.findByNameContaining(name), HttpStatus.OK); }

    public ResponseEntity<List<StuffInSpaceEntity>> getAllEntrys(){ return new ResponseEntity<List<StuffInSpaceEntity>>(repo.findAll(), HttpStatus.OK); }

    public ResponseEntity<String> deleteEntry(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return checkDeleteEntry(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID: " + id + " can\'t find in Database");
        }
    }

    private ResponseEntity<String> checkDeleteEntry(UUID id) {
        if (!repo.existsById(id)){
            log.info("StuffInSpace Entry deleted successful!");
            return new ResponseEntity<String>("Entry are successful deleted", HttpStatus.CONTINUE);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"ID: " + id + " can\'t deleted!");
        }
    }

    private ResponseEntity<String> checkNewEntry(StuffInSpaceEntity entity) {
        if(repo.existsById(entity.getUuid())){
            log.info("StuffInSpace Entry are created!");
            return new ResponseEntity<String>("New Item Stuff in Space are created", HttpStatus.CREATED);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entry can\'t created");
        }
    }

    public void updateEntry(StuffInSpaceEntity entity) {
        if(repo.existsById(entity.getUuid())){
            StuffInSpaceEntity entry = repo.getOne(entity.getUuid());
            entry.setName(entity.getName());
            entry.setInfo(entity.getInfo());
            entry.setPosition(entity.getPosition());
            repo.save(entry);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Enrty with UUID: " + entity.getUuid() + " doesn\'t existed!");
        }
    }

    public void deleteEntryByUUID(UUID id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            if (repo.existsById(id)){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Deleting wasn\'t successful!");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry with UUID: " + id + " dosen\'t exsists!");
        }
    }
}