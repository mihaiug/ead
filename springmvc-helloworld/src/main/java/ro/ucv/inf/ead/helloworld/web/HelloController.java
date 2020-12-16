package ro.ucv.inf.ead.helloworld.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.ucv.inf.ead.helloworld.service.HelloService;

@Controller
public class HelloController {

  private final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private HelloService helloService;

  @RequestMapping("/")
  public String index(Map<String, Object> model) {
    logger.debug("index() is executed!");

    model.put("message", helloService.sayHello(""));

    return "index";
  }

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
    logger.debug("Invoke sayHello() via '/hello' GET -  is executed - $name {}", name);
    
    model.addAttribute("message", helloService.sayHello(name));

    return "index";
  }

  @RequestMapping(value = "/hello", method = RequestMethod.POST)
  public ModelAndView sayHello(HttpServletRequest request, HttpServletResponse response) {    
    String name = request.getParameter("name");
    
    logger.debug("Invoke sayHello() via '/hello' POST -  is executed - $name {}", name);

    ModelAndView model = new ModelAndView("index");
    model.addObject("message", helloService.sayHello(name));

    return model;
  }

  @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
  public ModelAndView sayHello(@PathVariable("name") String name) {

    logger.debug("Invoke sayHello() via '/hello/{name:.+}' -  is executed - $name {}", name);

    ModelAndView model = new ModelAndView("index");
    model.addObject("message", helloService.sayHello(name));

    return model;
  }

  public void setHelloService(HelloService helloService) {
    this.helloService = helloService;
  }
}
