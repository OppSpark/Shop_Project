package com.oppspark.shop_porject.Repository;

import com.oppspark.shop_porject.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
