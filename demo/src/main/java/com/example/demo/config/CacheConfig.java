package com.example.demo.config;



import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CacheConfig extends JCacheConfigurerSupport{
	
	
	
	@Bean
    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
        return new ConcurrentCustomizer();
    }

    class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setAllowNullValues(false);
           
            //cacheManager.setStoreByValue(true);
            System.out.println("customizer invoked!!");
        }
    }
	
	
/*	@Bean
	public  CacheManager ehCacheManager() {
		CacheConfiguration tenSecondCache = new CacheConfiguration();
		tenSecondCache.setName("tenSecondCache");
		tenSecondCache.setTimeToLiveSeconds(10);
		tenSecondCache.setMaxEntriesLocalHeap(10);
		
		CacheConfiguration twentySecondCache = new CacheConfiguration();
		twentySecondCache.setName("twentySecondCache");
		twentySecondCache.setTimeToLiveSeconds(20);
		twentySecondCache.setMaxEntriesLocalHeap(10);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(tenSecondCache);
		config.addCache(twentySecondCache);
		
		return CacheManager.newInstance(config);
		
	}
	
	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager(){
		return new EhCacheCacheManager(ehCacheManager());
	}
*/	
}
