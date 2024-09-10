package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";


    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){

        beerService.patchBeerById(beerId,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId){

        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping (BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){

        beerService.updateBeerById(beerId,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    @PostMapping(BEER_PATH)
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer){

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/beer/"+savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


//    @RequestMapping("/api/v1/beer") //nu mai treben sa pun asta ca e deja global
    @GetMapping(BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();

    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException(){
//
//        System.out.println("In exception heandler ala");
//        return  ResponseEntity.notFound().build();
//    }

    @GetMapping(BEER_PATH_ID)
    public Beer geetBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Gett beer by id - in controller - test devtools");

        return beerService.getBeerById(beerId);
    }

}
