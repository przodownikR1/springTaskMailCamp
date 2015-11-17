package pl.java.scalatech.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MyCacheConfig.class)
@Slf4j
@ActiveProfiles("simple")
public class CacheTest {


    @Autowired
    private Repository repo;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void shouldCacheWork(){
        repo.put(new Car(1l,"fiat"));
        log.info("++++ car ->! - {}",repo.get(1l));

        repo.put(new Car(2l,"ford"));
        repo.put(new Car(3l,"polonez"));
        repo.put(new Car(4l,"tatra"));

        log.info("++++ car ->! - {}",repo.get(1l));
        log.info("caache {} ",cacheManager.getCache("store").get(1l).get());
        log.info("++++ car ->! - {}",repo.get(1l));
        log.info("++++ car ->! - {}",repo.get(1l));
        log.info("++++ car ->! - {}",repo.get(1l));

        log.info("++++ car ->! - {}",repo.get(2l));
        log.info("++++ car ->! - {}",repo.get(2l));
        log.info("+++ all : {}",repo.getAll());
        log.info("+++ all : {}",repo.getAll());
        log.info("+++ all : {}",repo.getAll());
        log.info("+++ all : {}",repo.getAll());
        log.info("+++ all : {}",repo.getAll());
        log.info("+++ all : {}",repo.getAll());


        //
    }



}
