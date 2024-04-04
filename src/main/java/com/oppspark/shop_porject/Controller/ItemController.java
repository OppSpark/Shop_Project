package com.oppspark.shop_porject.Controller;

import com.oppspark.shop_porject.Entity.Item;
import com.oppspark.shop_porject.Repository.ItemRepository;
import com.oppspark.shop_porject.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private ItemRepository itemRepository1;

    @GetMapping("/list")
    String list(Model model){
        itemService.itemList(model);
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    String detail(Model model, @PathVariable Long id ) {

        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("item", result.get());
            return "detail.html";
        } else{
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id){
        itemService.updateItem(model, id);
        return "edit.html";
    }

    @PostMapping("/api/item")
    String addItem(String title, Integer price){

        itemService.saveItem(title, price);

        return "redirect:/list";
    }

    @PutMapping("/api/edit/{id}")
    String editItem(
            String title,
            Integer price,
            @PathVariable Long id
    ){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setId(id);
        
        System.out.println("id = " + id);
        System.out.println("title = " + title);
        System.out.println("price = " + price);


        itemRepository.save(item);

        return "redirect:/list";
    }
}




