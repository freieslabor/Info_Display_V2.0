package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.CreateStuffInSpaceEntryException;
import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import Info_Display.V20.persistence.repositroy.StuffInSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    private ResponseEntity<StuffInSpaceEntity> checkNewEntry(StuffInSpaceEntity entity) throws CreateStuffInSpaceEntryException {
        if(repo.existsById(entity.getUuid())){
            return new ResponseEntity<StuffInSpaceEntity>(entity, HttpStatus.CREATED);
        }else{
            throw new CreateStuffInSpaceEntryException("Entry can\'t created");
        }
    }
}