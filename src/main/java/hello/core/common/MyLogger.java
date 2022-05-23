package hello.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@Slf4j
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(final String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(final String message) {
        log.info("[{}][{}] {}", this.uuid, this.requestURL, message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        log.info("[{}] request scope bean creat: [{}]", this.uuid, this);
    }

    @PreDestroy
    public void close() {
        log.info("[{}] request scope bean close: [{}]", this.uuid, this);
    }
}
