package datastructure;

public class CollectionUtils {
    public static <T> String toString(Iterable<T> iter) {
        StringBuilder sb = new StringBuilder();
        for(T item: iter) {
            sb.append(item.toString()).append(",");
        }
        if(sb.length() > 0) {
            sb.replace(sb.length()-1, sb.length(), "");
        }
        return "[" + sb.toString() + "]";
    }
}
