package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beer){

        beerService.patchBeerById(beerId,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId){

      if(!  beerService.deleteById(beerId)){
          throw new NotFoundException();
      }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping (BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId,@Validated @RequestBody BeerDTO beer){

       if( beerService.updateBeerById(beerId,beer).isEmpty()){
           throw new NotFoundException();
       }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    @PostMapping(BEER_PATH)
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@Validated @RequestBody BeerDTO beer){

        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/beer/"+savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


//    @RequestMapping("/api/v1/beer") //nu mai treben sa pun asta ca e deja global
    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) {
        return beerService.listBeers(beerName,beerStyle, showInventory, pageNumber, pageSize);

    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException(){
//
//        System.out.println("In exception heandler ala");
//        return  ResponseEntity.notFound().build();
//    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO geetBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Gett beer by id - in controller - test devtools");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

}
