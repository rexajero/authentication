package com.townhouse.management.house.requests;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.house.House;
import com.townhouse.management.house.vehicle.Vehicle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Request {
    @SequenceGenerator(
        name = "request_sequence",
        sequenceName = "request_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "request_sequence"
    )

    private Long id;
    private AppUserRole requestOwner;
    private Long requestOwnerId;
    private RequestType requestType;
    private RequestStatus requestStatus;
    private Long houseId;
    @OneToMany(mappedBy="request")
    private Set<MoveInItem> moveInItems = new HashSet<MoveInItem>();

    public Request(AppUserRole requestOwner, Long requestOwnerId, RequestType requestType, RequestStatus requestStatus,
            Long houseId) {
        this.requestOwner = requestOwner;
        this.requestOwnerId = requestOwnerId;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
        this.houseId = houseId;
    }

    
    
}
