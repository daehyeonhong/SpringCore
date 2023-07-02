## ComponentScan과 의존관계 자동 주입

- 지금까지는 Java => @Bean, XML => <bean/>을 통해 Bean을 등록했다.
- 이번에는 ComponentScan을 통해 Bean을 등록하는 방법을 알아보자.
- Spring은 설정 정보 없이 자동으로 스프링 빈을 등록하는 `ComponentScan` 기능을 제공한다.
- 또한 의존 관계도 자동으로 주입하는 `Autowired` 기능도 제공한다.

- 이전에 AppConfig에서 @Bean으로 직접 설정 정보 작성, 의존관계 직접 명시.
- @ComponentScan을 사용하면 설정 정보가 없기 때문에, 의존관계 주입도 클래스 안에서 해결 필요.
- `@Autowired`를 사용하면 스프링 컨테이너가 자동으로 의존관계를 주입해준다.

### *1. @ComponentScan*

![img_7.png](img_7.png)

- `@ComponentScan`은 `@Component` 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
- `@Configuration`이 붙은 클래스도 스프링 빈으로 등록된다.
- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자를 사용한다.
    - **빈 이름 기본 전략**: MemberServiceImpl 클래스 => memberServiceImpl
    - **빈 이름 직접 지정**: `@Component("memberService2")` => memberService2

### *2. @Autowired*

![img_8.png](img_8.png)

- 생성자에 `@Autowired`를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
- 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
    - **getBean(MemberRepository.class)**와 동일하다.
    - **빈 이름으로 조회**: `@Autowired` `private MemberRepository memberRepository;`

## 탐색 위치와 기본 스캔 대상

### 탐색할 패키지의 시작 위치 지정

- `basePackages`는 탐색할 패키지의 시작 위치를 지정한다.
    - 배열 형태로 여러 시작 위치를 지정할 수 있다.
- `basePackageClasses`는 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
    - 지정한 클래스의 패키지부터 하위 패키지를 모두 탐색한다.
    - 여러 클래스를 지정할 수 있다.
- 만약 지정하지 않으면 `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
  **권장하는 방법**
- 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
- 최근 스프링 부트는 `@SpringBootApplication`를 이 프로젝트 최상단에 두는 것이 관례이다.

### ComponentScan의 기본 대상

- `@Component` 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
    - `@Component`: 컴포넌트 스캔에서 사용
    - `@Controller`: 스프링 MVC 컨트롤러에서 사용
    - `@Service`: 스프링 비즈니스 로직에서 사용
    - `@Repository`: 스프링 데이터 접근 계층에서 사용
    - `@Configuration`: 스프링 설정 정보에서 사용
**참고**: 사실 애노테이션에는 상속관계라는 것이 없다. 이것은 스프링이 내부에서 처리하는 방법일 뿐이다.
    - Java의 Annotation은 상속이 불가능하다. 스프링이 내부에서 처리하는 방법일 뿐이다.
