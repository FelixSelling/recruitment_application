package kth.iv1201.group9.recruitment_application.domain.DTO;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;

public class PersonDTO {
    private final long personId;
    private final String name;
    private final String surname;
    private final String pnr;
    private final String email;
    private final String password;
    private final String username;

    public PersonDTO(Person person) {
        this.personId = person.getPersonId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.pnr = person.getPnr();
        this.email = person.getEmail();
        this.password = person.getPassword();
        this.username = person.getUsername();

        // TODO create DTOs for each entity and add to PersonDTO, below adds entity
        // directly
        // this.role = person.getRole();
        // this.competenceProfileList = person.getCompetenceProfileList();
        // this.AvailabilityList = person.getAvailabilityList();
    }

    public long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPnr() {
        return pnr;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}