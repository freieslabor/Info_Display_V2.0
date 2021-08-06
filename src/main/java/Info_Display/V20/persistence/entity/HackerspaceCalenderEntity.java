package Info_Display.V20.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class HackerspaceCalenderEntity {

    @Id
    private UUID uuid = UUID.randomUUID();
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime date;
    private String comment;

}
