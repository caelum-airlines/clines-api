package br.com.caelum.clines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airports")
public class Airport {
    @Id
    @Column(length = 3)
    @Type(type = "char")
    private String code;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
