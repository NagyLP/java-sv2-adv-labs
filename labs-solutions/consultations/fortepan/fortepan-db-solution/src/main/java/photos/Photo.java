package photos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @Column(name = "photo_year")
  private int year;

  private String nameOfPhotographer;

  @ElementCollection
  private List<String> additionalInfo = new ArrayList<>();

  public Photo(String description) {
    this.description = description;
  }

  public Photo(String description, int year) {
    this.description = description;
    this.year = year;
  }

  public Photo(Long id, String description, int year) {
    this.id = id;
    this.description = description;
    this.year = year;
  }

  public Photo(String description, int year, String nameOfPhotographer) {
    this.description = description;
    this.year = year;
    this.nameOfPhotographer = nameOfPhotographer;
  }

  public void addMoreInfo(String info) {
    additionalInfo.add(info);
  }
}
