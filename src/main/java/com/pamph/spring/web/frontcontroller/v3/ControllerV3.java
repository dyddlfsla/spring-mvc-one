package com.pamph.spring.web.frontcontroller.v3;

import com.pamph.spring.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

  ModelView process(Map<String, String> paramMap);

}
