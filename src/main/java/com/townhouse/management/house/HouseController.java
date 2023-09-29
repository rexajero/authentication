package com.townhouse.management.house;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/mytownhouse/house")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<HouseDTO> addHouse(@RequestBody HouseRegistrationRequest request) {
        return new ResponseEntity<HouseDTO>(houseService.registerNewHouse(request), HttpStatus.OK);
    }

    //add only the house
    @PostMapping(path = "add")
    public ResponseEntity<House> confirm(@RequestParam("block") int block, @RequestParam("lot") int lot) throws Exception {
        //register house
        //register owner
        return new ResponseEntity<>(houseService.registerOnlyHouse(block, lot), HttpStatus.CREATED);
    }

    @GetMapping(path = "get")
    public ResponseEntity<HouseDTO> get(@RequestParam("id") Long id) throws Exception {
        return new ResponseEntity<>(houseService.getHouseById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<HouseDTO>> getAllHouse() {
        return new ResponseEntity<List<HouseDTO>>(houseService.getAllHouse(), HttpStatus.OK);
    }
}
