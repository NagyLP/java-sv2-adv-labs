package photos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface FortepanRepository extends JpaRepository<Photo, Long> {

//    Keresd ki azokat a fényképeket, amelyeknek a leírásában szerepel
//    egy paraméterül átadott szövegrészlet (akár csak egy betű)!
    @Query("select p from Photo p where p.description like %:text%")
    List<Photo> listPhotosWithTextInDescription(String text);

//    Keresd ki azokat a fényképeket, amelyek a paraméterül átadott év után készültek!
    @Query("select p from Photo p where p.year > :year")
    List<Photo> listPhotosAfter(int year);

//    Add vissza a fényképészek listáját (minden név csak egyszer szerepeljen)!
    @Query("select distinct p.nameOfPhotographer from Photo p")
    List<String> listPhotographers();
}
