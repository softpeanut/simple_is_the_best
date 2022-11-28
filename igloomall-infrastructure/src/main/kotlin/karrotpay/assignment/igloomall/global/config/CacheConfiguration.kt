package karrotpay.assignment.igloomall.global.config

import karrotpay.assignment.igloomall.global.config.CacheValue.FIND_COUPON_HISTORIES
import karrotpay.assignment.igloomall.global.config.CacheValue.FIND_USER_RETAINED_COUPON
import net.sf.ehcache.Cache
import net.sf.ehcache.CacheManager
import net.sf.ehcache.config.CacheConfiguration.TransactionalMode
import net.sf.ehcache.config.DiskStoreConfiguration
import net.sf.ehcache.config.PersistenceConfiguration
import net.sf.ehcache.store.MemoryStoreEvictionPolicy
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.ehcache.EhCacheCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfiguration {

    @Bean
    protected fun ehCacheCacheManager(): EhCacheCacheManager? {
        val findCouponHistoriesCache = Cache(
            net.sf.ehcache.config.CacheConfiguration()
                .maxEntriesLocalHeap(1000)
                .maxEntriesLocalDisk(10000)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .diskSpoolBufferSizeMB(30)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                .transactionalMode(TransactionalMode.OFF)
                .persistence(
                    PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)
                )
                .name(FIND_COUPON_HISTORIES)
        )

        val findUserRetainedCouponsCache = Cache(
            net.sf.ehcache.config.CacheConfiguration()
                .maxEntriesLocalHeap(1000)
                .maxEntriesLocalDisk(10000)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                .transactionalMode(TransactionalMode.OFF)
                .persistence(
                    PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)
                )
                .name(FIND_USER_RETAINED_COUPON)
        )

        val manager = createCacheManager().apply {
            addCache(findCouponHistoriesCache)
            addCache(findUserRetainedCouponsCache)
        }

        return EhCacheCacheManager(manager)
    }

    private fun createCacheManager(): CacheManager {
        val configuration = net.sf.ehcache.config.Configuration().diskStore(
            DiskStoreConfiguration().path(DISK_STORE_PATH)
        )

        return CacheManager.create(configuration)
    }

    companion object {
        private const val DISK_STORE_PATH = "java.io.tmpdir"
    }
}

object CacheValue {
    const val FIND_USER_RETAINED_COUPON = "findUserRetainedCouponsCache"
    const val FIND_COUPON_HISTORIES = "findCouponHistoriesCache"
}
