package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;


@Controller
@RequestMapping("/phone")
public class PhoneController {
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		return "/WEB-INF/view/writeForm.jsp";
	}
	
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write(@ModelAttribute PersonVo personVo) { // 디스패쳐가 디폴트생성자를 찾는다. 생성자 파라미터가 몇개가 될지 모르기때문에! 메소드중 setter를 찾음 setName, setHp, setCompany 넣어주고 주소만 전달 실체는 온게아니야!!!!!!!!!!ox888이런 주소가 온거여
		System.out.println("/phone/write()");
		
		System.out.println(personVo.toString());
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> pList = phoneDao.getPersonList();
		System.out.println(pList.toString()); //controller가 dao를 부른다. dao에선 jdbc(sql 데이터)를 불러온다!
		
		
		model.addAttribute("pList", pList);
		return "/WEB-INF/view/list.jsp";
	}
	
	
	//update
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		
		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneDao.getPerson(personId);
		
		model.addAttribute("personVo", personVo);
		
		return "/WEB-INF/view/updateForm.jsp";
	}
	
	
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update()");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	
	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("/phone/delete()");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(personId);
		
		return "redirect:/phone/list";
	}
	
	
	
	
	

	
	/*@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write(@RequestParam("name") String name, 
						@RequestParam("hp") String hp, 
						@RequestParam(value="company", required = false, defaultValue = "0") String company) {
		
		System.out.println("/phone/write()");
		System.out.println(name + "," +  hp + "," + company);
		
		return "";
	}*/
	
	
	
	
	
}
