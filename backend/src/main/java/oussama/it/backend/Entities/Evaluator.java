package oussama.it.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("EVAL")
public class Evaluator extends User {
    private String institutionName ;
    private String institutionAddress ;
    private String Affiliation;


    @OneToOne(mappedBy = "evaluator")
    private Review review;
}
