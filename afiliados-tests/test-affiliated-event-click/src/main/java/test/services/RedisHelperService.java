package test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.cms.redis.driver.client.RedisInfoLocator;
import br.com.uol.cms.redis.driver.entry.RedisKey;
import br.com.uol.cms.redis.driver.entry.RedisValue;

@Service
public class RedisHelperService {

    private @Autowired RedisInfoLocator redisLocator;

    public String getValueByKey(final String key) {
        RedisValue value = redisLocator.locate(new BuildRedisKey(key));
        return value.getContent();
    }

    private class BuildRedisKey implements RedisKey {

        private final String key;

        public BuildRedisKey(final String key) {
            this.key = key;
        }

        @Override
        public String buildKey() {
            return key;
        }
    }
}
