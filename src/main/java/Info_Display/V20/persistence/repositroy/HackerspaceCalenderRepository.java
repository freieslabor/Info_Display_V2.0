package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface HackerspaceCalenderRepository extends JpaRepository<HackerspaceCalenderEntity, UUID> {

    public HackerspaceCalenderEntity findByDateAndComment(LocalDateTime date, String comment);

    public HackerspaceCalenderEntity findByDate(LocalDateTime date);

}
