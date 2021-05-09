package Info_Display.V20.persistence.service;

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
	
	public ResponseEntity<RoomStatus> getCurrentRoomStatus(){
		if(repo.getRoomStatus().equals(RoomStatus.OPEN)) {
			return new ResponseEntity<RoomStatus>(repo.getRoomStatus(), HttpStatus.OK);
		}else {
			return new ResponseEntity<RoomStatus>(repo.getRoomStatus(), HttpStatus.CONFLICT);
		}
		
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
}