package pl.java.scalatech.cache;

public interface Repository {

    Car get(long id);

    void put(Car value);

    void delete(long id);

}
