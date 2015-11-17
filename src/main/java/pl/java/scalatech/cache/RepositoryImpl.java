package pl.java.scalatech.cache;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
@CacheConfig(cacheNames = {"store"})
public class RepositoryImpl implements Repository{

    private ConcurrentMap<Long, Car> store = new ConcurrentHashMap<>();

    @Override
    @SneakyThrows
   @Cacheable(value="store", key="#id")
    public Car get(final Long id) {
        SECONDS.sleep(1);
        log.info("+++ cache miss !!");
        return store.get(id);
    }

    @Override
    @CachePut(value="store",key="#car.id")
    public Car put(Car car) {

        store.putIfAbsent(car.getId(), car);
         return car;

    }

    @Override
    @CacheEvict(value="store",key="#id")
    public void delete(Long id) {
        store.remove(id);

    }

    @Override
    @SneakyThrows
    @Cacheable(value="store")
    public Collection<Car> getAll() {
        SECONDS.sleep(2);
        log.info("+++ cache miss !!");
       Collection<Car> result  =  store.values();
       return result;
    }



}
