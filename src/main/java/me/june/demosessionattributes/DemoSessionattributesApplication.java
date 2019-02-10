package me.june.demosessionattributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SpringBootApplication
@Controller
@SessionAttributes(types = EventDTO.class)
public class DemoSessionattributesApplication {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ModelAttribute("eventDTO")
    public EventDTO event(){
        return new EventDTO();
    }

    @GetMapping("/list")
    public String list(
            EventDTO eventDTO
            , HttpServletRequest request
    ){
        Map<String,?> flahsMap = RequestContextUtils.getInputFlashMap(request);
        if(flahsMap != null){
            EventDTO flashDTO = (EventDTO) flahsMap.get("flashDTO");
            eventDTO.setName(flashDTO.getName());
        }

        logger.info("eventDTO's name = {}",eventDTO.getName());
        return "list.jsp";
    }

    @GetMapping("/index")
    public String index(
            Event event
    ){
        return "index.jsp";
    }

    @GetMapping("/ins")
    public String ins(
            EventDTO eventDTO
            , SessionStatus status
            , RedirectAttributes redirectAttributes
    ){
        redirectAttributes.addFlashAttribute("flashDTO",eventDTO);
        status.setComplete();
        return "redirect:/list";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoSessionattributesApplication.class, args);
    }

}

