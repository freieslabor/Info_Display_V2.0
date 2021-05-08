package Info_Display.V20.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HackerspaceRoomStatusEntity {
	
	private UUID uuid = UUID.randomUUID();
	private LocalDateTime creationDate = LocalDateTime.now();
	private String roomStatus;

}
