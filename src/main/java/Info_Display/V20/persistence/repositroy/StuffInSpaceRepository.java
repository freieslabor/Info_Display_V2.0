package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StuffInSpaceRepository extends JpaRepository<StuffInSpaceEntity, UUID> {

}
