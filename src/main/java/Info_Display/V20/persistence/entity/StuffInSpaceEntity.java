package Info_Display.V20.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="Stuff")
public class StuffInSpaceEntity {

    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private String position;
    private String info;

}
