package Info_Display.V20.persistence.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Info_Display.V20.lib.RoomStatus;
import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;

@Repository
public interface HackerspaceRoomStatusRepository extends JpaRepository<HackerspaceRoomStatusEntity, Integer>{
	
	@Query(value = "selcet Room_Status from Room_Status where id = (select max(id) from Room_Status)", nativeQuery = true)
	public RoomStatus getRoomStatus();

}
