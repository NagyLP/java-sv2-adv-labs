package locations;

import locations.dtos.CreateLocationCommand;
import locations.dtos.LocationDTO;
import locations.service.LocationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationsControllerWebClientIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    WebTestClient testClient;

    @Test
    void testCreateLocation() {
        when(locationsService.createLocation(any()))
                .thenReturn(new LocationDTO(1L, "Budapest", 47.497912, 19.040235));

        testClient
                .post()
                .uri("/api/locations")
                .bodyValue(new CreateLocationCommand(" "/* VAKSTRING-BlankString */ + " ", 90.01, 180.01))
                .exchange()
                .expectStatus().isBadRequest()
//                .expectBody()
//                    .jsonPath("id").isEqualTo(1L)
//                    .jsonPath("name").isEqualTo("Budapest")
//                    .jsonPath("lat").isEqualTo(47.497912)
//                    .jsonPath("lon").isEqualTo(19.040235)
//                .expectBodyList()
                .expectBody(LocationDTO.class)
//                    .value(l -> assertEquals("Budapest", l.getName()));
//                .isEqualTo(new LocationDTO(1L, "Budapest", 47.497912, 19.040235));
                .isEqualTo(new LocationDTO(null, null, 0.0, 0.0));

//        assertThat(locationsService.getLocations(Optional.of("Bu")))
//                .hasSize(1)
//                .extracting(LocationDTO::getName))
//                            .equals("Budapest");
    }



}
