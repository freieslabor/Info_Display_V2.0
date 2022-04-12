package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.SpaceInfromationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpaceInformationRepository extends JpaRepository<SpaceInfromationEntity, UUID> {
    boolean existsByTitle(String title);
    SpaceInfromationEntity findByTitle(String title);
}
