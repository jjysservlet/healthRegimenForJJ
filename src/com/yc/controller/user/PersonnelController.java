package com.yc.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yc.entity.user.Personnel;
import com.yc.service.IPersonnelService;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(PersonnelController.class);

    @Autowired
    IPersonnelService personnelService;
   
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("loginName");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession();
        session.removeAttribute("message");
        Personnel personnel = personnelService.getPersonnle(name);
        if (personnel == null) {
            request.getSession().setAttribute("message", "没有该用户！");
            return "redirect:/login";
        } else {
        	if (personnel.getForbidden() == false) {
        		if(personnel.getPassword().equals(pwd.trim())){    		 
        		 	session.setAttribute("loginPersonnle", personnel);
        		 	return "redirect:/management/index";
        		}   else {
                    request.getSession().setAttribute("message", "用户名或密码错误，请重新输入您的登陆信息！");
                    return "redirect:/login";
                }    		 
            }else {
       		 	request.getSession().setAttribute("message", "您已经被禁止了！");
                return "redirect:/login";
        	}
        }
    }
    @RequestMapping(value = "regist", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	return new ModelAndView("personnel/register", null);
    }

    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public String registing(Personnel personnel,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	personnelService.save(personnel);
        return "redirect:/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginPersonnle");
        return "redirect:/login";
    }
  
    @RequestMapping(value = "toAddPersonnle", method = RequestMethod.GET)
    public ModelAndView toAddPersonnle(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().getAttribute("loginPersonnle");
    	ModelMap mode = new ModelMap();
    	mode.put("personel", request.getSession().getAttribute("loginPersonnle"));
    	return new ModelAndView("Personnle/addPersonnle",mode);
    }
    
    @RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Personnel> list = personnelService.getAll();
		ModelMap mode = new ModelMap();
		mode.put("list", list);
		return new ModelAndView("management/userList", mode);
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public ModelAndView updateUser(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Personnel personnel = personnelService.findById(id);
		ModelMap mode = new ModelMap();
		mode.put("personnel", personnel);
		return new ModelAndView("management/updateUser", mode);
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUsers(Personnel personnel, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Personnel user = personnelService.getPersonnle(personnel.getLoginName());
		if (user != null) {
			personnel.setForbidden(user.getForbidden());
			BeanUtils.copyProperties(personnel, user);
			personnelService.update(user);
		}
		return "redirect:/personnel/userList";
	}

	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
	public String deleteUser(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		personnelService.delete(id);
		return "redirect:/personnel/userList";
	}
}
