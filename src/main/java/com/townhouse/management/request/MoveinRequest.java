package com.townhouse.management.request;

import com.townhouse.management.appuser.AppUser;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MoveinRequest {

    Long houseId;
    String requestType;
    AppUser requestedBy;
    
    
}
