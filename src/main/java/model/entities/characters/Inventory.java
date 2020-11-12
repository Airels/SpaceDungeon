package model.entities.characters;

import exceptions.InventoryFullException;
import model.items.Item;

import java.util.*;
import java.util.function.Consumer;

public class Inventory implements Iterable<Item> {
    private final List<Item> items;
    public final static int CAPACITY = 9;

    public Inventory() {
        items = new ArrayList<>();
    }

    public Inventory(Item... items) {
        this(Arrays.asList(items));
    }

    public Inventory(List<Item> items) {
        if (items.size() > CAPACITY)
            throw new InventoryFullException("Can't give more than " + CAPACITY + ". Actual: " + items.size());

        this.items = items;
    }

    public boolean addItem(Item item) {
        if (items.size() == CAPACITY)
            return false;

        items.add(item);
        return true;
    }

    public boolean addAll(List<Item> items) {
        if (this.items.size() + items.size() > 10)
            return false;

        this.items.addAll(items);
        return true;
    }

    public boolean addAll(List<Item> items, boolean lostItemsIfInventoryFull) {
        if (lostItemsIfInventoryFull)
            return addAll(items);

        int size = this.items.size();

        for (int i = 0; i <= (size - items.size()); i++)
            addItem(items.get(i));

        return true;
    }

    public Item getItem(int index) {
        if (index >= items.size()) return null;

        return items.get(index);
    }

    public Item removeItem(int index) {
        return items.remove(index);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        items.forEach(action);
    }

    @Override
    public Spliterator<Item> spliterator() {
        return items.spliterator();
    }
}
