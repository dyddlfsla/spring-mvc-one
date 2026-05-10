# Spring MVC 1st

Let’s study the various core principles contained in the Spring MVC.

## 📚 chapter

<details>
<summary>📂 chapter 1 : Understanding Web Application</summary>

- [1) What is web application?](docs/01-understanding-web-application/01-what-is-webapplication.md)
- [2) About Servlet](docs/01-understanding-web-application/02-what-is-servlet.md)
- [3) Multi-thread of WAS](docs/01-understanding-web-application/03-multi-thread-of-was.md)
- [4) HTTP Connection](docs/01-understanding-web-application/04-http-connection.md)
- [5) SSR and CSR](docs/01-understanding-web-application/05-ssr-and-csr.md)

</details>

---

<details>
<summary>📂 chapter 2 : About Servlet</summary>

- [1) Hello Servlet](docs/02-servlet/01-hello-servlet.md)
- [2) HTTP Request](docs/02-servlet/02-http-servlet-request.md)
- [3) Way to send request](docs/02-servlet/03-http-transfer-data.md)
- [4) Query string data](docs/02-servlet/04-query-string-request.md)
- [5) HTML form request](docs/02-servlet/05-form-request.md)
- [6) Message body request](docs/02-servlet/06-http-body-request.md)
- [7) Message JSON request](docs/02-servlet/07-http-json-request.md)
- [8) HTTP Response](docs/02-servlet/08-http-servlet-response.md)
- [9) HTML Response](docs/02-servlet/09-http-html-response.md)
- [10) JSON Response](docs/02-servlet/10-http-json-response.md)
- [11) Problem of Pure servlet](docs/02-servlet/11-servlet-problem.md)

</details>

---

<details>
<summary>📂 chapter 3 : JSP & MVC </summary>

- [1) apply JSP](docs/03-jsp-and-mvc/01-refactor-with-jsp.md)
- [2) apply MVC patterns](docs/03-jsp-and-mvc/02-refactor-with-mvc.md)

</details>

---

<details>
<summary>📂 chapter 4 : Front Controller Pattern </summary>

- [1) Front Controller v1](docs/04-front-controller/01-front-controller-v1.md)
- [2) Front Controller v2](docs/04-front-controller/02-front-controller-v2.md)
- [3) Front Controller v3](docs/04-front-controller/03-front-controller-v3.md)
- [4) Front Controller v4](docs/04-front-controller/04-front-controller-v4.md)
- [5) Front Controller v5](docs/04-front-controller/05-front-controller-v5.md)
- [6) Final summary](docs/04-front-controller/06-mvc-final-summary.md)

</details>

---

<details>
<summary>📂 chapter 5 : Spring MVC Structure </summary>

- [1) Spring MVC basic structure](docs/05-spring-mvc-structure/01-spring-mvc-basic.md)
- [2) Handler Mapping & HandlerAdapter](docs/05-spring-mvc-structure/02-spring-mvc-handler-mapping.md)
- [3) view Resolver](docs/05-spring-mvc-structure/03-spring-mvc-view-resolver.md)
- [4) Annotation Controller](docs/05-spring-mvc-structure/04-spring-mvc-annotation-controller.md)
- [5) Practical usage of controller](docs/05-spring-mvc-structure/05-spring-mvc-practical-usage.md)

</details>

---

## Fixed

- Tomcat logging level changed from DEBUG to TRACE
 
  ```application.properties
  logging.level.org.apache.coyote.http11=trace
  ```

- Spring Boot 3 migration: package namespace changed from `javax.*` to `jakarta.*`
  - Example:

    ```java
    // Before
    import javax.servlet.http.HttpServletRequest;

    // After
    import jakarta.servlet.http.HttpServletRequest;
    ```

  - Related APIs such as Servlet, JSP, JSTL, Validation, and JPA were also migrated to the `jakarta.*` namespace.


