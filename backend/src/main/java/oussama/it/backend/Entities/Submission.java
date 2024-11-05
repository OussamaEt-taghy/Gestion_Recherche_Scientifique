package oussama.it.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import oussama.it.backend.Entities.ENUMs.SubmissionStatus;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Submission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    private Article article;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="corresponding_id")
    private Corresponding corresponding;


    private Date submissionDate;

    
    private SubmissionStatus status;
}
