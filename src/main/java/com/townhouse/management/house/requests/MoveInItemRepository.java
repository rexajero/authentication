package com.townhouse.management.house.requests;

import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface MoveInItemRepository extends JpaRepository<MoveInItem, Long>{

}
