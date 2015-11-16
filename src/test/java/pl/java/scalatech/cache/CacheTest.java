package pl.java.scalatech.cache;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CacheConfig.class)
@Slf4j
public class CacheTest {

    
    @Autowired
    private Repository repo;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager; 
    
    
    @Test
    public void shouldCacheWork(){
        repo.put(new Car(1l,"fiat"));
        repo.put(new Car(2l,"ford"));
        repo.put(new Car(3l,"polonez"));
        repo.put(new Car(4l,"tatra"));
        log.info("++++  {}",repo.get(1));  
                   log.info("+++  {}",ehCacheCacheManager.getCacheNames());
                  EhCacheCache cache = (EhCacheCache) ehCacheCacheManager.getCache("store");
                  log.info("+++ {}",cache.get(2));
        //
    }
    
    
    
}
