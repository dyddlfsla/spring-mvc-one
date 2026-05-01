package com.pamph.spring.web.frontcontroller.v5;

import com.pamph.spring.web.frontcontroller.ModelView;
import com.pamph.spring.web.frontcontroller.MyView;
import com.pamph.spring.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.pamph.spring.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.pamph.spring.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.pamph.spring.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

  private final Map<String, Object> handlerMappingMap = new HashMap<>();
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontControllerV5() {
    initHandlerMappingMap();
    initHandlerAdapters();
  }

  private void initHandlerMappingMap() {
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
  }

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    System.out.println("FrontControllerServletV5.service call");

    Object handler = getHandler(request);

    if (handler == null) {
      response.setStatus(HttpServletResponse.SC_FOUND);
      return;
    }

    MyHandlerAdapter adapter = getMyHandlerAdapter(handler);
    ModelView mv = adapter.handle(request, response, handler);

    String viewName = mv.getViewName();
    MyView myView = viewResolver(viewName);

    myView.render(mv.getModel(), request, response);
  }

  private MyHandlerAdapter getMyHandlerAdapter(Object handler) {
    for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
      if (handlerAdapter.supports(handler)) {
        return handlerAdapter;
      }
    }
    throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler = " + handler);
  }

  private Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    return handlerMappingMap.get(requestURI);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
