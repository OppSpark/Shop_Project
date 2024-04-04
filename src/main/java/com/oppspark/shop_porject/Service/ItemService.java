package com.oppspark.shop_porject.Service;

import com.oppspark.shop_porject.Entity.Item;
import com.oppspark.shop_porject.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(
            String title,
            Integer price
    ){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);

        itemRepository.save(item);
    }

    public void itemList(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
    }

    public void updateItem(Model model, Long id) {
        Optional<Item> result = itemRepository.findById(id);
        itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다."));
        model.addAttribute("item", result.get());
    }
}
