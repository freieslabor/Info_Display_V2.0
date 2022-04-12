package Info_Display.V20.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="SpaceInformation")
public class SpaceInfromationEntity {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String info;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
