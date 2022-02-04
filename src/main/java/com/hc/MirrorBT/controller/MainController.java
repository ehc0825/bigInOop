package com.hc.MirrorBT.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Record;
import com.hc.MirrorBT.service.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService ms;
	@Autowired
	private HttpSession session;
	@RequestMapping("main.do")
	public String main(Model model) {
		List<Record> list= ms.selectRecord();
		model.addAttribute("list",list);
		return "/main/firstPage";
	}
	@RequestMapping("insertMember.do")
	public String insertMember(Model model,Player player) {
		int result=0;
		Random rand= new Random();
		
		int hp=rand.nextInt(4)+8;
		int mp=rand.nextInt(4)+8;
		
		int sheeld=rand.nextInt(2)+1;
		int atk=sheeld+2;
		int atkSpeed=rand.nextInt(3)+1;
		int dex=atk;
		player.setHp(hp);
		player.setMp(mp);
		player.setAtk(atk);
		player.setAtkSpeed(atkSpeed);
		player.setSheeld(sheeld);
		player.setDex(dex);
		if(player.getTribe().equals("human"))
		{
			player.setItemNo(1);
		}
		if(player.getTribe().equals("orc"))
		{
			player.setItemNo(2);
		}
		if(player.getTribe().equals("elf"))
		{
			player.setItemNo(3);
		}
		result=ms.insert(player);
		Player player1=ms.select(player.getNick());
		ms.wearItem(player1);
		String msg="당신은 모험을 시작했습니다";
		session.setAttribute("playerNo", player1.getPlayerNo());
		model.addAttribute("player",player1);
		model.addAttribute("msg",msg);
		return "/peace/peace";
	}
	@RequestMapping("peace.do")
	public String peace(Model model) {
		int playerNo=(Integer)session.getAttribute("playerNo");
		Player player=ms.selectbyNo(playerNo);
		String msg="당신은 모험은 계속됩니다";
		model.addAttribute("player",player);
		model.addAttribute("msg",msg);
		return "/peace/peace";
	}
	@RequestMapping("itemupdate.do")
	public String itemupdate(Model model,String weapon) {
		int playerNo=(Integer)session.getAttribute("playerNo");
		Player player=ms.selectbyNo(playerNo);
		ms.releaseItem(player);
		int result=0;
		if(weapon.equals("ShortSword"))
		{
			player.setItemNo(1);
			result=ms.updateweapon(player);
		}
		if(weapon.equals("LongSword"))
		{
			player.setItemNo(4);
			result=ms.updateweapon(player);
		}
		if(weapon.equals("ShortAxe"))
		{
			player.setItemNo(2);
			result=ms.updateweapon(player);
		}
		if(weapon.equals("IronHammer"))
		{
			player.setItemNo(5);
			result=ms.updateweapon(player);
		}
		if(weapon.equals("Shortbow"))
		{
			player.setItemNo(3);
			result=ms.updateweapon(player);
		}
		if(weapon.equals("IronBow"))
		{
			player.setItemNo(6);
			result=ms.updateweapon(player);
		}
		ms.wearItem(player);
		model.addAttribute("result",result);
		return "/main/itemupdate";
	}
	@RequestMapping("lose.do")
	public String lose(Model model) {
		int playerNo=(Integer)session.getAttribute("playerNo");
		Player player=ms.selectbyNo(playerNo);
		Record record=new Record();
		record.setStage(player.getLv());
		record.setNick(player.getNick());
		ms.insertRecord(record);
		List<Record> list= ms.selectRecord();
		model.addAttribute("list",list);
		return "/main/firstPage";
	}
	@RequestMapping("win.do")
	public String win(Model model) {
		int playerNo=(Integer)session.getAttribute("playerNo");
		Player player=ms.selectbyNo(playerNo);
		player.setLv(player.getLv()+1);
		player.setHp(player.getHp()+1);
		player.setMp(player.getMp()+1);
		player.setAtk(player.getAtk()+1);
		player.setAtkSpeed(player.getAtkSpeed()+1);
		if(player.getDex()<8)
		{
			player.setDex(player.getDex()+1);
		}
		else
		{
			player.setDex(8);
		}
		ms.levelup(player);
		String msg="승리한 당신의 모험은 계속됩니다";
		model.addAttribute("player",player);
		model.addAttribute("msg",msg);
		return "/peace/peace";
	}
	
}
