package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        final Item item = new Item("itemA", 10000, 10);

        // when
        final Item savedItem = itemRepository.save(item);

        // then
        final Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        final Item item1 = new Item("itemA", 10000, 10);
        final Item item2 = new Item("itemB", 20000, 20);

        // when
        itemRepository.save(item1);
        itemRepository.save(item2);

        final List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        final Item item = new Item("itemA", 10000, 10);
        final Item savedItem = itemRepository.save(item);
        final Long itemId = savedItem.getId();

        // when
        final Item updateParam = new Item("itemB", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        final Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
