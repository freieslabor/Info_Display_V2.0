package Info_Display.V20.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties(value = {"creationDate"})
public class HackerspaceCalenderEntity {

    @Id
    private UUID id = UUID.randomUUID();
    private LocalDateTime creationDate = LocalDateTime.now();
    private String date;
    private String name;
    private String comment;

}
