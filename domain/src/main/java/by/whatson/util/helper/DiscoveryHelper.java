package by.whatson.util.helper;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DiscoveryHelper {

    private DiscoveryClient discoveryClient;

    public Optional<URI> serviceUrl(String name) {
        return discoveryClient.getInstances(name)
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri);
    }
}
