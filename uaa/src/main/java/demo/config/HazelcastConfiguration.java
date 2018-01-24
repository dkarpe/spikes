package demo.config;

import static java.util.Collections.*;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config config() {
        Config config = new Config();
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList("127.0.0.1"));
        return config;
    }
}