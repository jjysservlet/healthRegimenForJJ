package com.yc.controller.user;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yc.entity.Collection;
import com.yc.entity.user.MembersUser;
import com.yc.service.IMembersUserService;
import com.yc.service.ICollectionService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	IMembersUserService userService;

	@Autowired
	ICollectionService collectionService;
	
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public String login(String page, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("loginName");
		String pwd = KL(JM(KL(MD5(request.getParameter("password")))));
		HttpSession session = request.getSession();
		MembersUser personnel = userService.getUser(name);
		if (personnel == null) {
			request.getSession().setAttribute("message", "用户不存在！！");
			return "redirect:/user/regist?page=" + page;
		} else {
			if (personnel.getPassword().equals(pwd)) {
				session.setAttribute("loginUser", personnel);
				session.removeAttribute("loginPage");
				if (page.equals("")) {
					return "redirect:/hthjin";
				} else {
					return "redirect:" + page;
				}
			} else {
				request.getSession().setAttribute("message", "密码不正确！！");
				return "redirect:/user/login?page=" + page;
			}
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("message");
		return new ModelAndView("user/login");
	}

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelMap mode = new ModelMap();
		Object obj = request.getSession().getAttribute("loginPage");
		if (obj != null) {
			mode.put("page", obj.toString());
		} else {
			mode.put("page", request.getHeader("Referer"));
		}
		return new ModelAndView("user/register", mode);
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("loginUser");
		return "redirect:/index";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String registing(String page, String phone, String password, String mobile_code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MembersUser user = userService.getUser(phone);
		if (user == null) {
			user = new MembersUser();
			user.setLoginName(phone);
			user.setPassword(KL(MD5(password)));
			user = userService.save(user);

		}
		return "redirect:/user/login?loginName=" + user.getLoginName() + "&password=" + user.getPassword() + "&page=" + page;
	}

	// 所有收藏
	@RequestMapping(value = "collection", method = RequestMethod.GET)
	public ModelAndView getAllCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelMap mode = new ModelMap();
		MembersUser user = (MembersUser) request.getSession().getAttribute("loginUser");
		List<Collection> collections = collectionService.getAllByUser(user.getMembersUserID());
		mode.put("collections", collections);
		mode.put("user", user);
		return new ModelAndView("user/collection", mode);
	}

	// 删除收藏
	@RequestMapping(value = "deleteCollection", method = RequestMethod.GET)
	public String deleteCollection(Integer collectionID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		collectionService.delete(collectionID);
		return "redirect:/user/collection";
	}

	// MD5加码。32位
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}
}
