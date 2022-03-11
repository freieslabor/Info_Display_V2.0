package Info_Display.V20.persistence.service;

import Info_Display.V20.persistence.entity.SpaceInfromationEntity;
import Info_Display.V20.persistence.repositroy.SpaceInformationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SpaceInformationService {

    @Autowired
    SpaceInformationRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    public void createSpaceInformation(SpaceInfromationEntity entity) {
        if (!repo.exisitsByTitle(entity.getTitle())){
            entity.setCreationDate(LocalDateTime.now());
            entity.setModificationDate(entity.getCreationDate());
            repo.save(entity);
            controlCreateSpaceInfo(entity.getId());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Space Information with the title: " + entity.getTitle() + " already exisits!");
        }
    }

    public void updateInformation(SpaceInfromationEntity entity) throws JsonProcessingException {
        if (repo.existsById(entity.getId())){
            SpaceInfromationEntity entityToUpdated = repo.getOne(entity.getId());
            entityToUpdated.setInfo(entity.getInfo());
            entityToUpdated.setTitle(entity.getTitle());
            entityToUpdated.setModificationDate(LocalDateTime.now());
            repo.save(entityToUpdated);
            controlUpdate(entityToUpdated);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry with uuid: " + entity.getId() + " dosen\'t exisits");
        }
    }

    public ResponseEntity<List<SpaceInfromationEntity>> getAllSpaceInformation(){ return  new ResponseEntity<>(repo.findAll(), HttpStatus.OK);}

    private void controlCreateSpaceInfo(UUID id) {
        if(!repo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Save failed!");
        }
    }

    private void controlUpdate(SpaceInfromationEntity entity) throws JsonProcessingException {
            JsonNode entityDB = mapper.readTree(String.valueOf(repo.getOne(entity.getId())));
            JsonNode entityToUpdate = mapper.readTree(String.valueOf(entity));
            if (!entityDB.equals(entityToUpdate)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Update failed! Entry to update are not equal to the entry from the database");
            }
    }

    public ResponseEntity<SpaceInfromationEntity> getInformationByTitle(String title){
        if (repo.exisitsByTitle(title)){
            return new ResponseEntity<>(repo.findByTitle(title), HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Space Information with the title: " + title + " doesn\'t exisits");
        }
    }
}