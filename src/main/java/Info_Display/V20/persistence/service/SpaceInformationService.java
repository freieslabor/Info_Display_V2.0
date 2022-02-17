package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.SpaceInformationException.CreateSpaceInformationException;
import Info_Display.V20.lib.Exception.SpaceInformationException.SpaceInformationException;
import Info_Display.V20.lib.Exception.SpaceInformationException.SpaceInformationExistsException;
import Info_Display.V20.persistence.entity.SpaceInfromationEntity;
import Info_Display.V20.persistence.repositroy.SpaceInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SpaceInformationService {

    @Autowired
    SpaceInformationRepository repo;

    public void createSpaceInformation(SpaceInfromationEntity entity) throws SpaceInformationException {
        if (!repo.findByTitle(entity.getTitle())){
            entity.setCreationDate(LocalDateTime.now());
            entity.setModificationDate(entity.getCreationDate());
            repo.save(entity);
            controlCreateSpaceInfo(entity.getId());
        }else{
            throw new SpaceInformationExistsException("Space Information with the title: " + entity.getTitle() + " already exisits!");
        }
    }

    public ResponseEntity<List<SpaceInfromationEntity>> getAllSpaceInformation(){ return  new ResponseEntity<>(repo.findAll(), HttpStatus.OK);}

    private void controlCreateSpaceInfo(UUID id) throws CreateSpaceInformationException {
        if(!repo.existsById(id)){
            throw new CreateSpaceInformationException("Save failed!");
        }
    }

}