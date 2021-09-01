package com.spring.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.FormDao;
import com.spring.dao.FormDaoImpl;
import com.spring.dao.UserDao;
import com.spring.dao.UserDaoImpl;
import com.spring.model.Form;
import com.spring.model.Response;
import com.spring.model.User;

@Controller
public class UserController {


	@RequestMapping("/")
	public ModelAndView authenticate() {
		return new ModelAndView("register","login",new User());
	}

	@RequestMapping(value="register",method=RequestMethod.POST)
	public String showMessage(@ModelAttribute("login") User user) {
		System.out.println(" Register Api hits");
		ModelAndView mv = null;
		if(user.getPhoneNo() != null && user.getPassword() != null) {
			mv = new ModelAndView("login","model",new User());
			UserDao userdoa = new UserDaoImpl();
			userdoa.addUser(user);
			System.out.println("User added");
		}
		return "login";
	}

	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView LoginGetReq() {
		return new ModelAndView("login","model",new User());
	}

	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView userValidation(@ModelAttribute("model") User user, HttpSession session) {
		System.out.println("Userhome hits");
		ModelAndView model = null;
		UserDao userdao = new UserDaoImpl();
		User u= userdao.getUser(user);
		if(u.getPhoneNo() != null && u.getPassword() != null) {
			if(u.getPhoneNo().equals(user.getPhoneNo()) && u.getPassword().equals(user.getPassword())) {
				model = new ModelAndView("userhome");
				session.setAttribute("user", user.getPhoneNo());
			}
		}
		else {
			model = new ModelAndView("wrong");
			model.addObject("wrong", "wrong credentials");
		}
		return model;	
	}
	
	@RequestMapping(value="user",method =RequestMethod.GET)
	public String userHome() {
		return "userhome";
	}
	
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request)
	{
		System.out.println("Logout hits");
		HttpSession session= request.getSession(false);
		if(session != null)
		{
			session.invalidate();
		}
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
	
	@RequestMapping(value="viewForm",method=RequestMethod.GET)
	public String viewForm() {
		return "viewForm";
	}

	@RequestMapping(value="formSubmit", method=RequestMethod.POST)
	public String formSubmit(@RequestParam("formId") String formId,@RequestParam("user") String user, @RequestParam("final_output") String form) {
		System.out.println(formId);
		String link = "http:localhost:9090/SimpleSpringProject/"+user+"/"+formId;
		System.out.println(form);
		System.out.println(link);

		Form f = new Form();
		f.setForm(form);
		f.setId(formId);
		f.setUser(user);

		FormDao formdao = new FormDaoImpl();
		formdao.addForms(f);
		System.out.println("Form Added for user "+user);

		return "redirect:formsList?user="+user;
	}

	@RequestMapping(value="formsList", method=RequestMethod.GET)
	public ModelAndView formsList(@RequestParam("user") String user) {
		ModelAndView mv = new ModelAndView("formsList");
		FormDao form = new FormDaoImpl();
		List<Form> forms = form.getForms(user);
		mv.addObject("forms",forms);
		return mv;
	}
	@RequestMapping(value="view/**",method=RequestMethod.GET)
	public ModelAndView viewPreviousForms(HttpServletRequest req) {
		String requestURL = req.getRequestURL().toString();
		System.out.println(requestURL);
		String userCode = requestURL.split("/view/")[1];
		String userArray[] = userCode.split("/");
		String user = userArray[0];
		String code = userArray[1];
		FormDao form = new FormDaoImpl();
		String form_inp = "";
		if(code.length() == 6) {
			form_inp = form.ViewForm(user,code);
		}
		System.out.println(form_inp);
		ModelAndView mv = new ModelAndView("viewUserForms");
		mv.addObject("form",form_inp);
		return mv ;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String deleteForm(@RequestParam("id") String id, @RequestParam("user") String user) {
		FormDao f = new FormDaoImpl();
		f.deleteForm(id);
		System.out.println("Form deleted "+id);
		return "redirect:formsList?user="+user;
	}
	
	@RequestMapping(value="response/**",method=RequestMethod.GET)
	public ModelAndView responseForms(HttpServletRequest req, HttpSession session) {
		String requestURL = req.getRequestURL().toString();
		System.out.println(requestURL);
		String userCode = requestURL.split("/response/")[1];
		String userArray[] = userCode.split("/");
		String code = userArray[0];
		FormDao form = new FormDaoImpl();
		String form_inp = "";
		if(code.length() == 6) {
			form_inp = form.ViewFormCode(code);
			session.setAttribute("code", code);
		}
		System.out.println(form_inp);
		ModelAndView mv = new ModelAndView("responseForm");
		mv.addObject("form",form_inp);
		return mv ;
	}
	@RequestMapping(value="response/userResponses",method=RequestMethod.POST)
	public String userResponses(@RequestParam("questions") String questions,@RequestParam("answers") String answers,
			@RequestParam("user") String user,
			@RequestParam("code") String code) {
		
		String[] ques = questions.split("#");
		String[] ans = answers.split("#");
		for(int i=0;i<ques.length;i++) {
			Response r = new Response();
			r.setAnswer(ans[i]);
			r.setCode(code);
			r.setUser(user);
			r.setQuestion(ques[i]);
			FormDao f = new FormDaoImpl();
			f.addResponse(r);
		}
		System.out.println("Responses Saved");
		return "done";
	}
	@RequestMapping(value="ViewResponse",method=RequestMethod.GET)
	public ModelAndView viewResponses(@RequestParam("id") String code) {
		FormDao f = new FormDaoImpl();
		List<Response> resUsers = f.viewResponseUsers(code);
		ModelAndView mv = new ModelAndView("ViewReponses");
		mv.addObject("Users",resUsers);
		mv.addObject("code",code);
		return mv;
	}
	
	@RequestMapping(value="ViewResponseUser",method=RequestMethod.GET)
	public ModelAndView viewResponsesUser(@RequestParam("id") String user,@RequestParam("code") String code) {
		FormDao f = new FormDaoImpl();
		List<Response> resAnswers = f.viewResponses(code,user);
		List<Response> resQuestion = f.viewResponseQuestions(code);
		int answers = resAnswers.size();
		int questions = resQuestion.size();
		int no_of_responses = answers/questions;
		ModelAndView mv = new ModelAndView("ViewReponsesUser");
		mv.addObject("questions",resQuestion);
		mv.addObject("tot_q",no_of_responses);
		mv.addObject("answers",resAnswers);
		return mv;
	}
	
	@RequestMapping(value="submitted", method=RequestMethod.GET)
	public ModelAndView UserSubmittedForms(@RequestParam("user") String user) {
		ModelAndView mv = new ModelAndView("UserFormSubmitted");
		FormDao form = new FormDaoImpl();
		List<String> sub = new ArrayList<>();
		List<Form> forms = form.getUserForms(user);
		for(Form f:forms) {
			String userId = form.getUser(f.getId());
			System.out.println(userId);
			String link = userId+"/"+f.getId();
			sub.add(link);
		}
		mv.addObject("subs",sub);
		return mv;
	}
	
}
