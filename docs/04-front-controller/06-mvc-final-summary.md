## mvc 프론트 컨트롤러 V1 ~ V5 의 변화

지금까지 V1 버전부터 시작해 V5 까지 코드를 발전시켜 보았다.  
각 버전마다 무엇이 문제였고 어떻게 해결했는지 다시 살펴보자.

### 1) V1 - 프론트 컨트롤러 도입
처음 만든 회원 저장 앱은 회원 등록 화면, 회원 저장, 전체 회원 조회 등 각각의 요청마다 별도의 서블릿을 만들었다.  
그러나 이러한 방식은 요청이 많아질수록 서블릿 클래스가 계속 늘어나고, 각 서블릿마다 공통 처리 코드가 반복된다는 문제가 있다.
예를 들어 모든 서블릿에서 공통적으로 다음과 같은 작업이 반복될 수 있다.

- 요청 파라미터 조회
- 비즈니스 로직 호출
- JSP로 forward
- 공통 예외 처리
- 인코딩 처리

이 문제를 해결하기 위해 모든 요청을 먼저 하나의 대표 컨트롤러가 받도록 만든다.  
이 대표 컨트롤러를 **`프론트 컨트롤러`**라고 한다.

프론트 컨트롤러는 요청 URL을 분석한 뒤, 실제 요청을 처리할 세부 컨트롤러를 찾아 호출한다.  
즉, 클라이언트의 요청 흐름은 다음과 같이 바뀐다.

 ```text
 클라이언트
    ↓
 FrontControllerServlet
    ↓
 각 요청에 맞는 Controller
    ↓
   JSP
 ```
 ☑️ 이 구조를 사용하면 공통 처리는 프론트 컨트롤러에 모으고, 각각의 컨트롤러는 실제 요청 처리에만 집중할 수 있다.

 ---

 ### 2) V2 - view 로직 분리
 V1에서는 프론트 컨트롤러를 도입했지만, 각각의 컨트롤러 내부에는 여전히 JSP로 이동하는 코드가 반복된다.

 ```java
 RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
 dispatcher.forward(request, response);
 ```
 이 코드는 모든 컨트롤러에서 거의 동일하게 반복된다.  
 컨트롤러가 해야 할 핵심 일은 요청을 처리하고, 어떤 화면을 보여줄지만 결정하는 것이다.
 
 따라서 JSP로 forward 하는 공통 로직을 별도의 View 객체로 분리한다.

 ```text
 Controller
    ↓
 MyView 반환
    ↓
 FrontController가 MyView.render() 호출
 
 -----------------------------------------
 기존 역할:
 컨트롤러가 요청 처리 + JSP forward까지 담당
 
 변경 후:
 컨트롤러는 요청 처리와 view 선택만 담당
 실제 forward는 View 객체가 담당
 ```

---

 ### 3) Model 추가

 V2까지는 View 로직을 분리했지만, 컨트롤러가 여전히 HttpServletRequest, HttpServletResponse 같은 서블릿 기술에 직접 의존한다.  
 예를 들어 컨트롤러는 JSP에 데이터를 전달하기 위해 다음과 같이 작성해야 한다.

 ```java
 request.setAttribute("member", member);
 ```
 이 방식은 컨트롤러가 서블릿 API에 강하게 묶인다는 단점이 있다.  
 그래서 V3에서는 컨트롤러가 직접 request 객체를 사용하지 않도록 Model을 도입한다.  
 컨트롤러는 Model에 데이터를 담고, view 이름을 반환한다.  

 ```java
 ModelView mv = new ModelView("save-result");
 mv.getModel().put("member", member);
 return mv;
 ```
 이제 컨트롤러는 서블릿 기술을 직접 몰라도 된다.  
 단순히 Model에 데이터를 담고, 이동할 view 이름만 반환하면 된다.  
 그리고 프론트 컨트롤러가 Model의 데이터를 request에 옮겨 담고, View를 렌더링한다.

---

### 4) 컨트롤러의 실용성 확장

 V3에서는 ModelView 객체를 반환하도록 만들었지만, 실제로 컨트롤러를 작성하다 보면 매번 ModelView 객체를 직접 생성하는 것이 번거롭다.  
 대부분의 컨트롤러는 결국 다음 두 가지 일만 한다.

 ```text
 1. model에 데이터 저장
 2. view 이름 반환
 ```
 그래서 V4에서는 컨트롤러 메서드의 파라미터로 model을 직접 넘겨주고, 컨트롤러는 view 이름만 반환하도록 만든다.

 ```java
 String process(Map<String, String> paramMap, Map<String, Object> model);
 ```
 
 ```java
 model.put("member", member);
 return "save-result";
 ```
 이 방식은 V3보다 훨씬 실용적이다.

 ```text
 V3:
 컨트롤러가 ModelView 객체를 직접 생성해서 반환
 
 V4:
 컨트롤러는 model에 데이터만 담고 viewName 문자열만 반환
 ```

--- 

 ### 5) 핸들러 어댑터를 통한 유연한 컨트롤러
 V1부터 V4까지는 프론트 컨트롤러가 특정한 컨트롤러 인터페이스만 호출할 수 있었다.  
 예를 들어 V3 프론트 컨트롤러는 V3 방식의 컨트롤러만 호출할 수 있고, V4 프론트 컨트롤러는 V4 방식의 컨트롤러만 호출할 수 있다.  
 문제는 컨트롤러마다 메서드 시그니처가 다를 수 있다는 점이다.
 
```java
 ModelView process(Map<String, String> paramMap);
```
```java
 String process(Map<String, String> paramMap, Map<String, Object> model);
```

 이처럼 컨트롤러의 형태가 다르면 프론트 컨트롤러가 직접 호출하기 어렵다.  
 이 문제를 해결하기 위해 핸들러 어댑터를 도입한다. 프론트 컨트롤러는 이제 컨트롤러를 직접 호출하지 않는다.  
 대신 해당 컨트롤러를 처리할 수 있는 어댑터를 찾아서 호출한다.
   
```text
 클라이언트
    ↓
 FrontController
    ↓
 HandlerMapping으로 핸들러 조회
    ↓
 HandlerAdapter 조회
    ↓
 HandlerAdapter가 실제 컨트롤러 호출
    ↓
 ModelView 반환
    ↓
 ViewResolver
    ↓
 View 렌더링
```

 이 구조 덕분에 서로 다른 방식의 컨트롤러도 하나의 프론트 컨트롤러 안에서 함께 사용할 수 있다.

 ☑️ 중요한 것은, V1 부터 V5을 거치면서 코드에 적용한 개념들이 스프링 MVC에서도 똑같이 적용된다는 것이다.    
 `DispatcherServlet`이 프론트 컨트롤러 역할을 하고, `HandlerMapping`, `HandlerAdapter`, `ViewResolver` 등이 함께 동작하면서 다양한 형태의 컨트롤러를 처리한다.
 
