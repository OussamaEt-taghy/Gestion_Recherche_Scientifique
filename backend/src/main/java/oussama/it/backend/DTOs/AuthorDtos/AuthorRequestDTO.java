package oussama.it.backend.DTOs.AuthorDtos;

import lombok.Data;

@Data
public class AuthorRequestDTO {
    private String name;
    private String institutionName;
    private String institutionAdress;
    private String affiliation;
}
