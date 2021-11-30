package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.StuffInSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StuffInSpaceRepository extends JpaRepository<StuffInSpaceEntity, UUID> {

    public List<StuffInSpaceEntity> findByNameContaining(String name);

}
