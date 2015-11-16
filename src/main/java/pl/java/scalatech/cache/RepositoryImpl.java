package pl.java.scalatech.cache;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RepositoryImpl<T,K> implements Repository<T, K>{

    private ConcurrentMap<T, K> store = new ConcurrentHashMap<>();
    
    @Override
    @SneakyThrows
    public K get(T key) {
        SECONDS.sleep(5);
        return store.get(key);
    }

    @Override
    public void put(T key, K value) {
           store.putIfAbsent(key, value);
        
    }

    @Override
    public void delete(T key) {
        store.remove(key);
        
    }

}
