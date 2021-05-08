package Info_Display.V20.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;

@RestController
public class HackerspaceRoomStatusController {
	
	@GetMapping(value = "/RoomStatus", produces = "applcation/json")
	public ResponseEntity<HackerspaceRoomStatusEntity> getRoomStatus(){
		
	}

}
