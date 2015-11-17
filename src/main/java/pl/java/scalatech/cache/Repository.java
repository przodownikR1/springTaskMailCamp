package pl.java.scalatech.cache;

import java.util.Collection;

public interface Repository {

    Car get(Long id);

    Car put(Car value);

    void delete(Long id);

    Collection<Car> getAll();

}
