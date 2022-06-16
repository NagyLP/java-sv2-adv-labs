package photos;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/photos")
public class FortepanController {

    private FortepanService service;

    @GetMapping
    public List<PhotoDto> listAllPhotos(@RequestParam Optional<String> photographer, @RequestParam Optional<Integer> year) {
        return service.listAllPhotos(photographer, year);
    }

    @GetMapping("/{id}")
    public PhotoDto findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PostMapping("/create-description")
    public PhotoDto createPhotoWithDescription(@RequestBody CreatePhotoWithDescriptionCommand command) {
        return service.createPhotoWithDescription(command);
    }

    @PostMapping("/create-description-and-year")
    public PhotoDto createPhotoWithDescriptionAndYear(@RequestBody CreatePhotoWithDescriptionAndYearCommand command) {
        return service.createPhotoWithDescriptionAndYear(command);
    }

    @PutMapping("/photographer-and-year/{id}")
    public PhotoDto updatePhotoWithPhotographerAndYear(@PathVariable("id") long id, @RequestBody UpdatePhotoWithPhotographerAndYearCommand command) {
        return service.updatePhotoWithPhotographerAndYear(id, command);
    }

    @PutMapping("/info/{id}")
    public PhotoDto updatePhotoWithInfo(@PathVariable("id") long id, @RequestBody UpdatePhotoWithInfoCommand command) {
        return service.updatePhotoWithInfo(id, command);
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable("id") long id) {
        service.deletePhoto(id);
    }
}
