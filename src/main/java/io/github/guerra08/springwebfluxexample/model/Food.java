package io.github.guerra08.springwebfluxexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {

    @Id
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Integer calories;

}
