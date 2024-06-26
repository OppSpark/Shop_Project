package com.oppspark.shop_project.Controller;

import com.oppspark.shop_project.Entity.Item;
import com.oppspark.shop_project.Repository.ItemRepository;
import com.oppspark.shop_project.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

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

    @PostMapping("/api/edit")
    String editItem(
            String title,
            Integer price,
            Long id
    ){
        itemService.editItem(title, price, id);
        return "redirect:/detail/" + id;
    }

    @DeleteMapping("/api/delete")
    String deleteItem(@RequestBody Map<String, Object> body){
        Long id = Long.parseLong(Objects.requireNonNull(body.get("id")).toString());
        itemService.deleteItem(id);
        return "redirect:/list";
    }

    //테스트용 API
}
