package com.oppspark.shop_porject.Service;

import com.oppspark.shop_porject.Entity.Item;
import com.oppspark.shop_porject.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    public void saveItem(String title, Integer price){
        Item item = new Item();

        item.setTitle(title);
        item.setPrice(price);

        itemRepository.save(item);
    }
}
