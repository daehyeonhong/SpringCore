package hello.core.service;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(final String id) {
        final MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log(String.format("service id = %s", id));
    }
}
