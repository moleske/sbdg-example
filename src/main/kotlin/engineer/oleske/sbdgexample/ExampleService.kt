package engineer.oleske.sbdgexample

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.ConcurrentHashMap


@Component
class ExampleService {
    private val namedCounterMap = ConcurrentHashMap<String, AtomicLong>()

    @CachePut("Counters")
    fun getCount(counterName: String): Long {
        var counter = this.namedCounterMap[counterName]

        if (counter == null) {
            counter = AtomicLong(0L)

            counter = namedCounterMap.putIfAbsent(counterName, counter) ?: counter
        }

        return counter.incrementAndGet()
    }

    @Cacheable("Counters")
    fun getCachedCount(counterName: String): Long = getCount(counterName)

    @CacheEvict("Counters")
    fun resetCounter(counterName: String) {
        namedCounterMap.remove(counterName)
    }

}
