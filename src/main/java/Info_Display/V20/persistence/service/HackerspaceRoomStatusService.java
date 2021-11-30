package Info_Display.V20.persistence.service;

import java.util.List;
import java.util.logging.Logger;

import Info_Display.V20.lib.Exception.RoomStatusExceptions.ChangeRoomStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Info_Display.V20.lib.Enum.RoomStatus;
import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceRoomStatusRepository;

@Service
public class HackerspaceRoomStatusService {
	
	@Autowired
	HackerspaceRoomStatusRepository repo;

	private Logger log = Logger.getLogger(String.valueOf(this.getClass()));
	
	public HackerspaceRoomStatusEntity getCurrentRoomStatus(){
		return repo.findFirstByOrderByCreationDateDesc().get(0);
	}
	
	public ResponseEntity<String> setStatus(RoomStatus roomStatus) throws ChangeRoomStatusException {
		HackerspaceRoomStatusEntity entity = new HackerspaceRoomStatusEntity();
		entity.setRoomStatus(roomStatus);
		repo.save(entity);
		if(repo.existsById(entity.getId())) {
			return new ResponseEntity<>("Room Status is " + roomStatus, HttpStatus.CREATED);
		}else {
			throw new ChangeRoomStatusException("Room Status can\'t changed");
		}
	}
	
	public List<HackerspaceRoomStatusEntity> getAllEntrys(){
		log.info("Get request for all RoomStatus in database");
		return repo.findAll();
	}
}