package com.townhouse.management.house.requests;

import java.util.Set;

import com.townhouse.management.appuser.AppUserRole;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddNewHouseRequest {
    private final AppUserRole requestOwnerRole;
    private final Long requestOwnerRoleId;
    private final Long houseId;
    private final RequestType requestType;
    private final Set<MoveInItem> moveInItems;
}
