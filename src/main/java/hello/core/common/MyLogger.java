package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(final String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(final String message) {
        System.out.printf("[%s][%s] %s%n", this.uuid, this.requestURL, message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean creat: %s%n", this.uuid, this);
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] request scope bean close: %s%n", this.uuid, this);
    }
}
