package generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddDuplicateAndFindThenPositive() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Positive"));
        store.add(new Role("3", "Negative"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Positive");
    }

    @Test
    void whenFindWithIncorrectIdThenNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Positive"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceItemThenNewItem() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Positive"));
        store.replace("1", new Role("1", "Neutral"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Neutral");
    }

    @Test
    void whenDeleteItemThenStoreIsEmpty() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Negative"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }
}