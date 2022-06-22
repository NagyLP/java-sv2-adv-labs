package sv2advfinalexam.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Column(name="postal_code")
    private String postalCode;
    private String city;
    private String street;
    @Column(name="house_number")
    private int houseNumber;
}
