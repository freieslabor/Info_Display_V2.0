package Info_Display.V20.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;
import Info_Display.V20.persistence.service.HackerspaceRoomStatusService;

@RestController
public class HackerspaceRoomStatusController {
	
	@Autowired
	HackerspaceRoomStatusService roomStatuService;
	
	@GetMapping(value = "/RoomStatus", produces = "applcation/json")
	public ResponseEntity<HackerspaceRoomStatusEntity> getRoomStatus(){
		return roomStatuService.getCurrentRoomStatus();
	}

}
