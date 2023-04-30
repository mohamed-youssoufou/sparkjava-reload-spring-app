package ci.nocod3r.config;

import java.util.List;
import java.util.Map;

public class Response<T> {
    private boolean hasError;
    private T item;
    private List<T> items;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem() {
        return item;
    }

    public List<T> getItems() {
        return items;
    }
}
