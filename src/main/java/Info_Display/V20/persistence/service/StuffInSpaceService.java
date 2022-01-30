package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.CreateStuffInSpaceEntryException;
import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.DeleteEntryException;
import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.FindEntryException;
import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import Info_Display.V20.persistence.repositroy.StuffInSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class StuffInSpaceService {

    @Autowired
    StuffInSpaceRepository repo;

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    public ResponseEntity<String> createNewEntry(StuffInSpaceEntity entity) throws CreateStuffInSpaceEntryException {
        StuffInSpaceEntity entry = new StuffInSpaceEntity();
        entry.setName(entity.getName());
        entry.setInfo(entity.getInfo());
        entry.setPosition(entity.getPosition());

        repo.save(entry);

        return checkNewEntry(entry);

    }

    public ResponseEntity<List<StuffInSpaceEntity>> getEntryByContaining(String name){ return new ResponseEntity<List<StuffInSpaceEntity>>(repo.findByNameContaining(name), HttpStatus.OK); }

    public ResponseEntity<List<StuffInSpaceEntity>> getAllEntrys(){ return new ResponseEntity<List<StuffInSpaceEntity>>(repo.findAll(), HttpStatus.OK); }

    public ResponseEntity<String> deleteEntry(UUID id) throws FindEntryException, DeleteEntryException {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return checkDeleteEntry(id);
        } else {
            throw new FindEntryException("ID: " + id + " can\'t find in Database");
        }
    }

    private ResponseEntity<String> checkDeleteEntry(UUID id) throws DeleteEntryException {
        if (!repo.existsById(id)){
            log.info("StuffInSpace Entry deleted successful!");
            return new ResponseEntity<String>("Entry are successful deleted", HttpStatus.CONTINUE);
        }else{
            throw new DeleteEntryException("ID: " + id + " can\'t deleted!");
        }
    }

    private ResponseEntity<String> checkNewEntry(StuffInSpaceEntity entity) throws CreateStuffInSpaceEntryException {
        if(repo.existsById(entity.getUuid())){
            log.info("StuffInSpace Entry are created!");
            return new ResponseEntity<String>("New Item Stuff in Space are created", HttpStatus.CREATED);
        }else{
            throw new CreateStuffInSpaceEntryException("Entry can\'t created");
        }
    }

    public void updateEntry(StuffInSpaceEntity entity) throws FindEntryException {
        if(repo.existsById(entity.getUuid())){
            StuffInSpaceEntity entry = repo.getOne(entity.getUuid());
            entry.setName(entity.getName());
            entry.setInfo(entity.getInfo());
            entry.setPosition(entity.getPosition());
            repo.save(entry);
        }else{
            throw new FindEntryException("Enrty with UUID: " + entity.getUuid() + " doesn\'t existed!");
        }
    }
}