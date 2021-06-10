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

@Service
public class StuffInSpaceService {

    @Autowired
    StuffInSpaceRepository repo;

    public ResponseEntity<StuffInSpaceEntity> createNewEntry(String name, String info, String position) throws CreateStuffInSpaceEntryException {
        StuffInSpaceEntity entry = new StuffInSpaceEntity();
        entry.setName(name);
        entry.setInfo(info);
        entry.setPosition(position);

        return checkNewEntry(entry);

    }

    public ResponseEntity<StuffInSpaceEntity> getEntryByID(UUID id) throws FindEntryException {
        if (repo.existsById(id)){
            return new ResponseEntity<StuffInSpaceEntity>(repo.findById(id).get(), HttpStatus.OK);
        }else{
            throw new FindEntryException("ID: " + id + " can\'t find in Database");
        }
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
            return new ResponseEntity<String>("Entry are successful deleted", HttpStatus.CONTINUE);
        }else{
            throw new DeleteEntryException("ID: " + id + " can\'t deleted!");
        }
    }

    private ResponseEntity<StuffInSpaceEntity> checkNewEntry(StuffInSpaceEntity entity) throws CreateStuffInSpaceEntryException {
        if(repo.existsById(entity.getUuid())){
            return new ResponseEntity<StuffInSpaceEntity>(entity, HttpStatus.CREATED);
        }else{
            throw new CreateStuffInSpaceEntryException("Entry can\'t created");
        }
    }
}