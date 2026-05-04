## 스프링 MVC의 애노테이션 컨트롤러

앞서 스프링의 레거시 컨트롤러를 구현하고 동작 방식을 알아보았다.  
그러나 현재 최신 스프링 애플리케이션 개발에서는 그러한 과거 방식은 거의 사용되지 않고,  
**애노테이션 기반 컨트롤러**를 가장 많이 사용한다.

---

### @RequestMapping

스프링은 애노테이션을 활용하여 매우 유연하고 실용적인 컨트롤러를 제공하는데,  
이것이 바로 `@RequestMapping` 애노테이션을 사용하는 컨트롤러이다.

과거에는 스프링의 MVC 지원이 부족했기 때문에 Struts 같은 별도의 웹 프레임워크에 의존하는 경우가 많았다.  

하지만 애노테이션 기반 컨트롤러가 도입된 이후  
👉 **스프링 MVC 자체가 사실상 표준이 되었다.**

---

### 애노테이션 컨트롤러 동작 구조

스프링 MVC는 다음 과정을 거친다:

1. 핸들러 매핑 조회
2. 핸들러 어댑터 조회
3. 핸들러 실행

애노테이션 기반 컨트롤러에서는 다음 두 컴포넌트가 사용된다.

- `RequestMappingHandlerMapping`
- `RequestMappingHandlerAdapter`

👉 이 둘이 **애노테이션 기반 컨트롤러의 핵심 구성요소**이다.

---

#### 1) SpringMemberFormControllerV1

```java
@Controller // 이 애노테이션이 붙은 클래스는 스프링 Bean으로 등록된다. (@Controller 는 내부적으로 @Component 를 포함한다.)
public class SpringMemberFormControllerV1 {

  @RequestMapping("/springmvc/v1/members/new-form") // URL 요청 정보를 메서드에 매핑한다.
  public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("new-form");
  }
}
```

---

## 🔍 핸들러 탐색 방식 (버전 차이 중요)

과거 버전:

> RequestMappingHandlerMapping 은 등록된 스프링 Bean 중에서  
> `@RequestMapping` 이나 `@Controller` 가 붙은 클래스를 핸들러로 검색한다.

❗ 이 설명은 **현재(Spring Boot 3.3.1 / Spring 6 기준)에서는 틀린 설명이다.**

---

### ✔️ 과거 (Spring 4 ~ 일부 5)

```java
protected boolean isHandler(Class<?> beanType) {
    return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) || 
           AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class);
}
```

👉 `@RequestMapping`만 있어도 핸들러로 인식됨

---

### ✔️ 현재 (Spring 6 / Boot 3.x)

```java
protected boolean isHandler(Class<?> beanType) {
    return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
}
```

👉 **오직 `@Controller` (또는 `@RestController`)만 핸들러로 인정된다.**

👉 따라서 `@RequestMapping`만 붙인 클래스는 더 이상 핸들러가 아니다.

---

##### 2) SpringMemberSaveControllerV1

```java
@Controller
public class SpringMemberSaveControllerV1 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/springmvc/v1/members/save")
  public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);

    memberRepository.save(member);
    ModelAndView mv = new ModelAndView("save-result");
    mv.addObject("member", member);
    return mv;
  }
}
```

---

## 정리

### 1) 왜 `@Controller`가 필수일까?

- `@RequestMapping`은 **URL 매핑 정보**일 뿐이고
- `@Controller`는 **“이 클래스가 웹 요청을 처리하는 핸들러다”** 라는 선언이다.

👉 Spring 6부터는 역할을 명확히 분리하기 위해  
👉 `@Controller`가 있어야만 핸들러로 인정한다.

---

### 2) HandlerMapping vs HandlerAdapter 역할

| 구성 요소 | 역할 |
|----------|------|
| HandlerMapping | 요청 URL → 실행할 컨트롤러 메서드 찾기 |
| HandlerAdapter | 실제 메서드 실행 (리플렉션 사용) |

---

### 3) 인터페이스 없이 컨트롤러가 동작하는 이유

과거에는 다음과 같은 인터페이스가 필요했다:

```java
public interface ControllerV3 {
    ModelAndView process(Map<String, String> paramMap);
}
```

하지만 현재는:

- 컨트롤러 메서드 시그니처 자유
- 인터페이스 구현 필요 없음

👉 이유:

- 스프링이 **리플렉션**으로 메서드를 직접 호출함
- `HandlerMethod` 객체에 → (컨트롤러 객체 + 메서드) 정보를 저장
- 실행 시 `method.invoke()`로 호출

---

### 4) 파라미터는 어떻게 주입되는가?

```java
public ModelAndView process(HttpServletRequest request, HttpServletResponse response)
```

👉 이 파라미터들은 개발자가 넣는 것이 아니라

👉 **HandlerMethodArgumentResolver**가 자동으로 생성해서 전달한다.

대표적으로:
- HttpServletRequest
- HttpServletResponse
- @RequestParam
- @ModelAttribute

---

## 🔥 한 줄 핵심 정리

- `@Controller` 없으면 핸들러 아님 (Spring 6 기준)
- `@RequestMapping`은 매핑 정보일 뿐
- 실행은 리플렉션 기반
- 파라미터는 자동 바인딩

👉 즉, **“메서드만 작성하면 스프링이 나머지를 다 처리해준다”**