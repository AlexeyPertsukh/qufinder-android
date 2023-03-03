package com.explodeman.qufinder.model.item_repository.item_memory_repository;

import com.explodeman.qufinder.model.Item;
import com.explodeman.qufinder.model.item_repository.AbstractItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemMemoryRepository extends AbstractItemRepository {

  private final List<Item> items = createItems();

  public ItemMemoryRepository() {
  }

  @Override
  protected List<Item> getItems() {
    return items;
  }

  private List<Item> createItems() {
    List<Item> items = new ArrayList<>();
    items.add(new Item("Кот?", "Да"));
    items.add(new Item("Алексей", "Филипп"));
    items.add(new Item("Ирек", "Сергей"));
    items.add(new Item("Сколько будет 1 + 1", "2"));
    items.add(new Item("Сколько будет 2 + 2", "4"));
    return items;
  }
}
