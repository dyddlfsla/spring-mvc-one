## 스프링 MVC 컨트롤러의 실용적인 사용 방식

애노테이션 기반의 컨트롤러는 웹 개발을 지원하기 위한 다양한 기능을 제공한다.  
자주 사용되는 대표적인 기능들을 살펴보자.

---

### 1. 메서드 단위 매핑 (메서드 통합)

애노테이션 기반 컨트롤러를 보면 `@RequestMapping`이 클래스가 아닌 메서드 단위로 적용된다는 것을 알 수 있다.  
이 특징을 활용하면 하나의 컨트롤러에서 여러 URL 요청을 처리할 수 있다.

```java
@Controller
public class SpringMemberFormControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();
  
  // 1. 회원 등록 화면 요청
  @RequestMapping("/springmvc/v2/members/new-form")
  public ModelAndView newForm(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("new-form");
  }
  
  // 2. 회원 저장 요청
  @RequestMapping("/springmvc/v2/members/save")
  public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);

    memberRepository.save(member);
    ModelAndView mv = new ModelAndView("save-result");
    mv.addObject("member", member);
    return mv;
  }
  
  // 3. 전체 회원 조회 요청
  @RequestMapping("/springmvc/v2/members")
  public ModelAndView list() {

    List<Member> members = memberRepository.findAll();

    ModelAndView mv = new ModelAndView("members");
    mv.addObject("members", members);

    return mv;
  }
}
```

---

### 2. 공통 URL 분리 (중복 제거)

각 메서드의 URL에 `/springmvc/v2/members`가 반복되고 있으므로, 공통 경로를 클래스 레벨로 분리할 수 있다.

```java
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberFormControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/new-form")
  public ModelAndView newForm(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("new-form");
  }

  @RequestMapping("/save")
  public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);

    memberRepository.save(member);
    ModelAndView mv = new ModelAndView("save-result");
    mv.addObject("member", member);
    return mv;
  }

  @RequestMapping
  public ModelAndView list() {

    List<Member> members = memberRepository.findAll();

    ModelAndView mv = new ModelAndView("members");
    mv.addObject("members", members);

    return mv;
  }
}
```

---

### 3. viewName 직접 반환

`ModelAndView` 대신 viewName(String)만 반환할 수 있다.

```java
@RequestMapping("/new-form")
public String newForm() {
  return "new-form";
}
```

---

### 4. `@RequestParam` 애노테이션

`request` 객체를 직접 사용하지 않고도 애노테이션을 통해 요청 파라미터를 편리하게 추출할 수 있다.

```java
@RequestMapping("/save")
public String save(@RequestParam("username") String username,
                   @RequestParam("age") int age,
                   Model model) {

  Member member = new Member(username, age);
  memberRepository.save(member);
  
  model.addAttribute("member", member);
  
  return "save-result";
}
```

---

### 5. HTTP 메서드 지정 (`@GetMapping`, `@PostMapping`)

`@RequestMapping`은 기본적으로 URL 기준으로 매핑되지만, `method` 속성이나 전용 애노테이션을 통해 HTTP 메서드를 명확하게 지정할 수 있다.

```java
// GET 요청만 허용
@RequestMapping(value = "/new-form", method = RequestMethod.GET)
public String newForm() {
  return "new-form";
}
```

```java
@GetMapping("/members")
public ModelAndView list() {

  List<Member> members = memberRepository.findAll();

  ModelAndView mv = new ModelAndView("members");
  mv.addObject("members", members);

  return mv;
}
```

---

## ✔️ 정리

- `@RequestMapping`은 메서드 단위로 요청을 매핑한다.
- 하나의 컨트롤러에서 여러 기능을 함께 처리할 수 있다.
- 공통 URL은 클래스 레벨로 분리하여 중복을 제거할 수 있다.
- `@RequestParam`을 사용하면 요청 파라미터를 간편하게 추출할 수 있다.
- HTTP 메서드는 `@GetMapping`, `@PostMapping` 등으로 명확히 구분하는 것이 좋다.
  - @GetMapping 외에도 @Post/Put/Patch/Delete + Mapping 도 가능함.

---