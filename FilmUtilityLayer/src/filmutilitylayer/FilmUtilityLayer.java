package filmutilitylayer;

import filmclasslayer.FilmClassLayer.*;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class FilmUtilityLayer
{
    //static Cache test = CacheData();
    
    public static Cache CacheData()
    {
        Films filmcacheddata;
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Films.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);
        
            
            Cache<String, Films> myCache
                    = cacheManager.getCache("preConfigured", String.class, Films.class);
            
        return myCache;
    }
    
    public static void put(Films filmdata, Cache myCache)
    {
        myCache.put("preConfigured", filmdata);
    }
    
    public static Films get(Cache myCache)
    {
        return (Films)myCache.get("preConfigured");
    }
    
    public String[] SplitNames(String value)
    {
        String Firstname = "", Surname;
        String[] temp;
        temp = value.split(" ");
        Surname = temp[temp.length - 1];
        
        for(int i = 0; i < (temp.length - 1); i++)
        {
            Firstname = Firstname + temp[i] + " ";
        }
        String FinalOutput[] = {Firstname, Surname};
        return FinalOutput;
    }
}
