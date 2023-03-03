package com.explodeman.qufinder.model.item_repository;

import com.explodeman.qufinder.model.Item;

import java.util.List;

public interface ItemRepository {
  List<Item> findByFilter(String find);
}
