package oussama.it.backend.DTOs.AuthorDtos;

import lombok.Data;

import java.util.List;
@Data
public class AuthorGroupRequestDTO {
    private String groupename;
    private Long CorrespondingIds;
    private List<Long> membresIds;
}
