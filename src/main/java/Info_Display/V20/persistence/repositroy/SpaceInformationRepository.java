package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.SpaceInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpaceInformationRepository extends JpaRepository<SpaceInformationEntity, UUID> {
    boolean existsByTitle(String title);
    SpaceInformationEntity findByTitle(String title);
}
