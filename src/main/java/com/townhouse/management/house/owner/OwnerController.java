package com.townhouse.management.house.owner;

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
@RequestMapping(path = "api/v1/mytownhouse/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody OwnerRegistrationRequest request) throws Exception {
        return new ResponseEntity<OwnerDTO>(ownerService.add(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return new ResponseEntity<List<OwnerDTO>>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    @GetMapping(path = "get-by-house")
    public ResponseEntity<OwnerDTO> getOwnerByHouseId(@RequestParam("id") Long id) {
        return new ResponseEntity<OwnerDTO>(ownerService.getOwnerByHouseId(id), HttpStatus.OK);
    }
}
