package com.beben.tomasz.restaurant.test.module;

import com.beben.tomasz.restaurant.test.module.utils.JacksonObjectMapper;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.ws.rs.client.Client;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(
        classes = {
                IntegrationTestApplication.class,
                TestCqrsConfiguration.class,
                TestPersistenceConfiguration.class,
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class BaseIntegrationTest extends AbstractTestNGSpringContextTests {

    @LocalServerPort
    private int localPort;

    protected Client client() {
        return JerseyClientBuilder.createClient()
                .register(new JacksonObjectMapper())
                .property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true)
                .property(ClientProperties.MOXY_JSON_FEATURE_DISABLE, true);
    }

    protected URI getRestUri() throws URISyntaxException {
        return new URI("http", null, "localhost", localPort, "/", null, null);
    }
}