package Info_Display.V20.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;
import Info_Display.V20.persistence.repositroy.HackerspaceRoomStatusRepository;

@Service
public class HackerspaceRoomStatusService {
	
	@Autowired
	HackerspaceRoomStatusRepository repo;
	
	public ResponseEntity<HackerspaceRoomStatusEntity> getCurrentRoomStatus(){
		return null;
	}

}