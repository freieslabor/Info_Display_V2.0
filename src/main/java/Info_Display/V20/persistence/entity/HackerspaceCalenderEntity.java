package Info_Display.V20.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime creationDate = LocalDateTime.now();

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;

    private String comment;

}
