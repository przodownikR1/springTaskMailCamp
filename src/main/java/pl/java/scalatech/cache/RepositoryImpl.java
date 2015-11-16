package pl.java.scalatech.cache;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RepositoryImpl implements Repository{

    private ConcurrentMap<Long, Car> store = new ConcurrentHashMap<>();
    
    @Override
    @SneakyThrows
    @Cacheable(value="store",key="#key")
    public Car get(long key) {
        SECONDS.sleep(5);
        return store.get(key);
    }

    @Override
    @CachePut(value="store",key="#value.id")
    public void put(Car value) {
        store.putIfAbsent(value.getId(), value);
        
    }

    @Override
    @CacheEvict(value="store",key="#key")
    public void delete(long key) {
        store.remove(key);
        
    }

}
