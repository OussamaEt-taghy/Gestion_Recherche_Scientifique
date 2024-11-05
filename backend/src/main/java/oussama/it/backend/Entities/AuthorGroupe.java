package oussama.it.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class AuthorGroupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupename;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="correspondingAuthor_id")
    @JsonManagedReference
    private Corresponding correspondingAuthor;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "author_group_author",
            joinColumns = @JoinColumn(name = "author_group_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonManagedReference
    private List<Author> membres = new ArrayList<>();
}

