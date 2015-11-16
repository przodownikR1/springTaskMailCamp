package pl.java.scalatech.cache;

public interface Repository<T, K> {

    K get(T key);

    void put(T key,K value);

    void delete(T key);

}
