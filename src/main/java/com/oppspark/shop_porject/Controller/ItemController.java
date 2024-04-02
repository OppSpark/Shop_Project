package com.oppspark.shop_porject.Controller;

import com.oppspark.shop_porject.Entity.Item;
import com.oppspark.shop_porject.Repository.ItemRepository;
import com.oppspark.shop_porject.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model){List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);
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


    @PostMapping("/api/item")
    String addItem(String title, Integer price){

        itemService.saveItem(title, price);

        return "redirect:/list";
    }

}




