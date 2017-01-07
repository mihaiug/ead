package ro.ucv.inf.ead.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloService {

  private static final Logger logger = LoggerFactory.getLogger(HelloService.class);
  
  public HelloService(){
    logger.debug("Bean helloService was created");
  }

  public String sayHello(String name) {

    logger.debug("sayHello() is executed! $name : {}", name);

    if (StringUtils.isEmpty(name)) {
      return "Hello World!";
    } else {
      return "Hello " + name + "!";
    }
  }

}