package pl.mariuszpisz.sampleapp.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class RoleDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String system;
}