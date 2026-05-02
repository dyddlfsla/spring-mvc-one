package com.pamph.spring.web.frontcontroller.v5.adapter;

import com.pamph.spring.web.frontcontroller.ModelView;
import com.pamph.spring.web.frontcontroller.v4.ControllerV4;
import com.pamph.spring.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof ControllerV4;
  }

  @Override
  public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {

    ControllerV4 controller = (ControllerV4) handler;

    Map<String, String> paramMap = createParamMap(request);

    //model: 요청 처리가 끝나고 사용자에게 화면을 보여줄때 필요한 데이터
    HashMap<String, Object> model = new HashMap<>();

    String viewName = controller.process(paramMap, model);

    ModelView mv = new ModelView(viewName);
    mv.setModel(model);

    return mv;
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();

    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }
}
