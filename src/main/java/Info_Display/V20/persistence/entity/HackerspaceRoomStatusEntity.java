package Info_Display.V20.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Info_Display.V20.lib.RoomStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="RoomStatus")
public class HackerspaceRoomStatusEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private LocalDateTime creationDate = LocalDateTime.now();
	private RoomStatus roomStatus;
	

}