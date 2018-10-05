package com.spring.item.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.item.service.entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {

	Items findByitemName(String itemName);
}
