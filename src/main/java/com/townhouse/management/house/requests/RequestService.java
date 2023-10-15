package com.townhouse.management.house.requests;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseDaoImpl;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.owner.OwnerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final HouseDaoImpl houseDaoImpl;
    private final OwnerRepository ownerRepository;
    private final MoveInItemRepository moveInItemRepository;

    public Request create(AddNewHouseRequest request) throws Exception {
        House house = getHouse(request.getHouseId());
        Request req = null;

        if(request.getRequestOwnerRole() == AppUserRole.OWNER) {
            Owner owner = getOwner(request.getRequestOwnerRoleId());

            req = new Request(
                request.getRequestOwnerRole(), 
                owner.getId(), 
                request.getRequestType(), 
                RequestStatus.ADMIN_APROVAL, 
                house.getId()
            );

            req = requestRepository.save(req);
            Set<MoveInItem> itemsSet = addRequestItems(req, request.getMoveInItems());
            req.setMoveInItems(itemsSet);
            
            //send email to admin
        } else {
            return null;
        }

        return requestRepository.findById(req.getId()).orElseThrow(() -> new Exception("Request not found"));
    }

    private Set<MoveInItem> addRequestItems(Request req, Set<MoveInItem> moveInItems) {
        Set<MoveInItem> itemsSet = new HashSet<MoveInItem>();
        
        for(MoveInItem item : moveInItems) {
            MoveInItem tempItem = moveInItemRepository.save(
                new MoveInItem(
                    req, 
                    item.getName(), 
                    item.getDescription(), 
                    item.getQuantity(), 
                    item.getRemarks())
            );
            itemsSet.add(tempItem);
        }
        return itemsSet;
    }

    private Owner getOwner(Long id) throws Exception {
        return ownerRepository.findById(id).orElseThrow(() -> new Exception("House not found"));
    }

    private House getHouse(Long houseId) throws Exception {
        House house = houseDaoImpl.get(houseId).orElseThrow(() -> new Exception("House not found"));
        return house;
    }
}
