package Info_Display.V20.persistence.repositroy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Info_Display.V20.lib.RoomStatus;
import Info_Display.V20.persistence.entity.HackerspaceRoomStatusEntity;

@Repository
public interface HackerspaceRoomStatusRepository extends JpaRepository<HackerspaceRoomStatusEntity, Integer>{
	
	public List<HackerspaceRoomStatusEntity> findFirstByOrderByCreationDateDesc();

}
