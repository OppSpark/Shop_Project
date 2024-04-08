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

    public void saveItem(String title, Integer price){
        if(title.length() > 100 || title.isEmpty())
            throw new IllegalArgumentException("제목이 비어있거나 100자 이하로 입력해주세요.");
        if(price < 0 || price > 2000000000)
            throw new IllegalArgumentException("가격은 0원 이상 2,000,000,000 이하로 입력해주세요.");

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
        itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 아이템이 없습니다."));
        model.addAttribute("item", result.get());
    }

    public void editItem(String title, Integer price, Long id){
        if(title.length() > 100 || title.isEmpty())
            throw new IllegalArgumentException("제목이 비어있거나 100자 이하로 입력해주세요.");
        if(price < 0 || price > 2000000000)
            throw new IllegalArgumentException("가격은 0원 이상 2,000,000,000 이하로 입력해주세요.");

        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setId(id);

        itemRepository.save(item);
    }

    public void deleteItem(Long id){
        if(!itemRepository.existsById(id))
            throw new IllegalArgumentException("해당 아이템이 없습니다.");
        itemRepository.deleteById(id);
    }
}
