package pl.java.scalatech.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CacheConfig.class)
@Slf4j
public class CacheTest {

    
    @Autowired
    private Repository<String, String> repo;
    
    
    
    @Test
    public void shouldCacheWork(){
        repo.put("slawek", "borowiec");
        repo.put("agnieszka", "borowiec");
        repo.put("mike", "tyson");
        log.info("++++ {}",repo.get("mike"));
    }
    
    
    
}
