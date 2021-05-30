package Info_Display.V20.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="Stuff")
public class StuffInSpaceEntity {

    private UUID uuid;
    private String name;
    private String position;
    private String info;

}
