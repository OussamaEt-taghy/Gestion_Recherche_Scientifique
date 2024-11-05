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
@Data
@DiscriminatorValue("AUTH")
@Component
public class Corresponding extends User implements Serializable {


  @OneToMany(mappedBy = "correspondingAuthor",fetch = FetchType.LAZY)
  @JsonIgnore
  private List<AuthorGroupe> groups = new ArrayList<>();


  @OneToMany(mappedBy = "corresponding",fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Submission> submissions = new ArrayList<>();
}
