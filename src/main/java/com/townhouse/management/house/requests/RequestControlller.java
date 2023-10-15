package com.townhouse.management.house.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/mytownhouse/request")
public class RequestControlller {
    private final RequestService requestService;

    public RequestControlller(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody AddNewHouseRequest request) throws Exception {
        return new ResponseEntity<Request>(requestService.create(request), HttpStatus.CREATED);
    }

}
