package hello.core.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();

    public static SingleTonService getInstance() {
        return instance;
    }

    private SingleTonService() {
    }

    public void logic() {
        log.info("싱글톤 객체 로직 호출");
    }

}
