package br.com.caelum.clines.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Type(type = "char")
    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Country country;

    @NotNull
    @Type(type = "char")
    @Column(length = 2)
    private String state;

    @NotNull
    private String city;

    public Location(Country country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }
}
