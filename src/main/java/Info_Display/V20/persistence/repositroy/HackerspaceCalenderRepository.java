package Info_Display.V20.persistence.repositroy;

import Info_Display.V20.persistence.entity.HackerspaceCalenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface HackerspaceCalenderRepository extends JpaRepository<HackerspaceCalenderEntity, UUID> {

    public HackerspaceCalenderEntity findByDateAndName(String date, String name);
    public List<HackerspaceCalenderEntity> findByDateContaining(String date);
    public HackerspaceCalenderEntity findByDate(LocalDateTime date);

}
