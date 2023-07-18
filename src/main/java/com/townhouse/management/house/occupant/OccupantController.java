package com.townhouse.management.house.occupant;

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
@RequestMapping(path = "api/v1/mytownhouse/occupant")
public class OccupantController {
    private final OccupantService occupantService;

    public OccupantController(OccupantService occupantService) {
        this.occupantService = occupantService;
    }

    @PostMapping
    public ResponseEntity<String> addOccupants(@RequestBody List<OccupantRegistrationRequest> request) throws Exception {
        return new ResponseEntity<String>(occupantService.add(request), HttpStatus.OK);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<OccupantDTO>> getAllOccupantsByHouse() {
        return new ResponseEntity<List<OccupantDTO>>(occupantService.getAllOccupants(), HttpStatus.OK);
    }

    @GetMapping(path = "get")
    public ResponseEntity<OccupantDTO> getAllOccupantById(@RequestParam("id") Long id) throws Exception {
        return new ResponseEntity<OccupantDTO>(occupantService.getOccupantsById(id), HttpStatus.OK);
    }

    @GetMapping(path = "get-by-house")
    public ResponseEntity<List<OccupantDTO>> getAllOccupantsByHouse(@RequestParam("id") Long id) {
        return new ResponseEntity<List<OccupantDTO>>(occupantService.getOccupantsByHouseId(id), HttpStatus.OK);
    }
}
