package Info_Display.V20.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Info_Display.V20.lib.RoomStatus;
import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceRoomStatusRepository;

@Service
public class HackerspaceRoomStatusService {
	
	@Autowired
	HackerspaceRoomStatusRepository repo;
	
	public HackerspaceRoomStatusEntity getCurrentRoomStatus(){
		return repo.findFirstByOrderByCreationDateDesc().get(0);
	}
	
	public ResponseEntity<String> setStatus(RoomStatus roomStatus){
		HackerspaceRoomStatusEntity entity = new HackerspaceRoomStatusEntity();
		entity.setRoomStatus(roomStatus);
		repo.save(entity);
		if(repo.existsById(entity.getId())) {
			return new ResponseEntity<>("Room Status is " + roomStatus, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Room status can\'t changed", HttpStatus.CONFLICT);
		}
	}
	
	public List<HackerspaceRoomStatusEntity> getAllEntrys(){
		return repo.findAll();
	}
}