package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;


    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, ()->{
            beerController.geetBeerById(UUID.randomUUID());
        });



    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO dto = beerController.geetBeerById(beer.getId());


        assertThat(dto).isNotNull();

    }

    @Test
    void testListBeers() {

        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(3);

    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(0);

    }
}