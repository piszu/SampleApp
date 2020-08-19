package pl.mariuszpisz.sampleapp.role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.mariuszpisz.sampleapp.commons.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@SuperBuilder
@NoArgsConstructor
@SequenceGenerator(name = "seq", sequenceName = "auth_seq", allocationSize = 1)
@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity {

    private String name;
    private String system;

}