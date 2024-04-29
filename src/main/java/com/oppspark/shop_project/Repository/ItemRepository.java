package com.oppspark.shop_project.Repository;

import com.oppspark.shop_project.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
