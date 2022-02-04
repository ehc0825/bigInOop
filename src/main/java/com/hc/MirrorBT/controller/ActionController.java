package com.hc.MirrorBT.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.MirrorBT.model.Battle;
import com.hc.MirrorBT.model.Monster;
import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Result;
import com.hc.MirrorBT.service.MainService;

@Controller
public class ActionController {
	@Autowired
	private MainService ms;
	@Autowired
	private HttpSession session;
	@RequestMapping("action.do")
	public String action(Model model,int lv,String state) {
		if(state.equals("peace"))
		{
			int playerNo=(Integer)session.getAttribute("playerNo");
			Player player=ms.selectbyNo(playerNo);
			Monster monster=ms.monsterset(player);
			ms.insertMonster(monster);
			Monster monster1=ms.selectMon(monster);
			player.setNowHp(player.getHp());
			player.setNowMp(player.getMp());
			monster1.setNowHp(monster1.getHp());
			monster1.setNowMp(monster1.getMp());
			Battle battle=new Battle();
			battle.setPlayerNo(player.getPlayerNo());
			battle.setMonsterNo(monster1.getMonsterNo());
			battle.setStage(1);
			ms.insertBattle(battle);
			Battle battle1=ms.selectBattle(battle);
			model.addAttribute("battle",battle1);
			model.addAttribute("monster",monster1);
			model.addAttribute("player",player);
			return "/battle/battle";
		}
		else {
			return "/peace/peace";
		}
	}
	@ResponseBody
	@RequestMapping(value = "heal.do", method = { RequestMethod.POST })
	public Result heal(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
		if(playernowHp<player.getHp())
		{
			if (playernowMp >= 3) {
				player.setNowHp(playernowHp + 1);
				player.setNowMp(playernowMp - 3);
				result.setMsg("힐성공");
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("힐실패 마나부족");
			}
			
		}
		else
		{
			player.setNowHp(playernowHp);
			player.setNowMp(playernowMp);
			result.setMsg("힐실패 이미 최대 hp입니다");
		}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		
		result.setBattle(battle);

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "attack.do", method = { RequestMethod.POST })
	public Result attack(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
		Random random=new Random();
		int randomRe=random.nextInt(3);	
		if(randomRe==1)
		{
			int dmg=ms.dmg(player.getSheeld(), player.getAtk());
			dmg=ms.reflex(dmg);
			player.setNowHp(playernowHp-dmg);
			monster.setNowHp(monsternowHp);
			result.setMsg("공격실패 반격받았습니다");
		}
		else
		{
			int dmg=ms.dmg(monster.getSheeld(), player.getAtk());	
			monster.setNowHp(monsternowHp-dmg);
			player.setNowHp(playernowHp);
			result.setMsg("공격성공");
		}
		player.setNowMp(playernowMp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		
		result.setBattle(battle);

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "steam.do", method = { RequestMethod.POST })
	public Result steam(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 6) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 6);
				result.setMsg("플레이어 광폭화");
				ms.steam(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unsteam(player); 
				            }
				        }, 
				        5000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("광폭화실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		
		result.setBattle(battle);

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "guard.do", method = { RequestMethod.POST })
	public Result guard(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 5) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 5);
				result.setMsg("가드스킬발동");
				ms.guard(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unguard(player); 
				            }
				        }, 
				        5000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("가드실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "invincible.do", method = { RequestMethod.POST })
	public Result invincible(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 15) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 15);
				result.setMsg("무적스킬발동");
				ms.invincible(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.uninvincible(player); 
				            }
				        }, 
				        10000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("무적스킬 실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
	@ResponseBody
	@RequestMapping(value = "anger.do", method = { RequestMethod.POST })
	public Result anger(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 5) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 5);
				result.setMsg("분노스킬발동");
				ms.anger(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unanger(player); 
				            }
				        }, 
				        5000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("분노스킬 실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "frenzy.do", method = { RequestMethod.POST })
	public Result frenzy(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 15) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 15);
				result.setMsg("격분스킬발동");
				ms.frenzy(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unfrenzy(player); 
				            }
				        }, 
				        60000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("격분스킬 실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
	@ResponseBody
	@RequestMapping(value = "elusion.do", method = { RequestMethod.POST })
	public Result elusion(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 5) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 5);
				result.setMsg("일루전스킬발동");
				ms.elusion(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unelusion(player); 
				            }
				        }, 
				        5000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("일루전스킬 실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "rapid.do", method = { RequestMethod.POST })
	public Result rapid(@RequestParam("playernowHp") int playernowHp,@RequestParam("playernowMp") int playernowMp,
			@RequestParam("monsternowHp") int monsternowHp,@RequestParam("monsternowMp") int monsternowMp,
			@RequestParam("monsterNo") int monsterNo,@RequestParam("playerNo") int playerNo,
			@RequestParam("battleNo") int battleNo) {
		final Player player=ms.selectbyNo(playerNo);
		Monster monster=ms.selectMonNo(monsterNo);
		Battle battle=ms.selectBattleNo(battleNo);
		Result result=new Result();
			if (playernowMp >= 5) {
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp - 5);
				result.setMsg("레피드스킬발동");
				ms.rapid(player); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				               ms.unrapid(player); 
				            }
				        }, 
				        60000 
				);
			}
			else
			{
				player.setNowHp(playernowHp);
				player.setNowMp(playernowMp);
				result.setMsg("레피드스킬 실패 마나부족");
			}
		monster.setNowHp(monsternowHp);
		monster.setNowMp(monsternowMp);
		result.setMonster(monster);
		result.setPlayer(player);
		battle.setStage(battle.getStage()+1);
		ms.updateBattle(battle);
		result.setBattle(battle);

		return result;
	}
}
