package com.xgw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.xgw.model.Test;


public interface TestService {
  public List<Test> getAllTest();
  public int  update() ;
}
