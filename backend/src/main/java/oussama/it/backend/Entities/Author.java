package oussama.it.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String institutionName;
    private String institutionAdress;
    private String affiliation;

    @ManyToMany(mappedBy = "membres",  cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<AuthorGroupe> groups = new ArrayList<>();
}
