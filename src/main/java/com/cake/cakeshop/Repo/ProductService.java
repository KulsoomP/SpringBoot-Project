package com.cake.cakeshop.Repo;

import com.cake.cakeshop.Model.AddItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductService  extends JpaRepository<AddItem , Integer> {
//    AddItem getAddItemById(String productId);
Optional<AddItem> findById(Integer id);
    List<AddItem> findAll();

}
