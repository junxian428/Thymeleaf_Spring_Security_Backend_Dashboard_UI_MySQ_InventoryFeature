package com.example.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erp.entity.Stock;
import com.example.erp.entity.User;
import com.example.erp.repository.StockRepository;

import jakarta.validation.Valid;

@Controller
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;


        @GetMapping("/addStock")
    public String addStockForm(Stock stock) {
        return "stock/addstock";
    }
    
    @PostMapping("/addStock")
    public String addStock(@Valid Stock stock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/Stock";
        }
        
        stockRepository.save(stock);
        return "redirect:/Stock";
    }

    // additional CRUD methods
    @GetMapping("/stockindex")
    public String showstockList(Model model) {
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/stockmain";
    }

        @GetMapping("/stockedit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("stocks", stockRepository.findAll());
        Stock stock = stockRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("stock", stock);
        return "backend/updatestock";
    }


    @PostMapping("/updateStock/{id}")
public String updateUser(@PathVariable("id") int id, @Valid Stock stock, 
  BindingResult result, Model model) {
    if (result.hasErrors()) {
        stock.setId(id);
        return "/Stock";
    }
        
    stockRepository.save(stock);
    return "redirect:/Stock";
}
    
@GetMapping("/deletestock/{id}")
public String deleteUser(@PathVariable("id") int id, Model model) {
    Stock stock = stockRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    stockRepository.delete(stock);
    return "redirect:/Stock";
}


}