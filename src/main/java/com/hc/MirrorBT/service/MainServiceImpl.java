package com.hc.MirrorBT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.MirrorBT.dao.MainDao;
import com.hc.MirrorBT.model.Battle;
import com.hc.MirrorBT.model.Monster;
import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Record;

@Service
public class MainServiceImpl implements MainService {
@Autowired
	private MainDao md;

@Override
public int insert(Player player) {
	// TODO Auto-generated method stub
	return md.insert(player);
}

@Override
public Player select(String nick) {
	// TODO Auto-generated method stub
	return md.select(nick);
}

@Override
public Player selectbyNo(int playerNo) {
	// TODO Auto-generated method stub
	return md.selectbyNo(playerNo);
}

@Override
public Monster monsterset(Player player) {
	Monster monster=new Monster();
	monster.setAtk(player.getAtk()+1);
	monster.setDex(player.getDex()+1);
	monster.setHp(player.getHp()+1);
	monster.setLv(player.getLv());
	monster.setMp(player.getMp()+1);
	monster.setMonsterNAME(player.getNick()+"의 거울상");
	monster.setSheeld(player.getSheeld());
	if(player.getLv()==100)
	{
		monster.setTribe("last");
	}
	else if(player.getLv()%5==0)
	{
		monster.setTribe("boss");
	}
	else
	{
		monster.setTribe("monster");
	}
	return monster;
}

@Override
public void insertMonster(Monster monster) {
	md.insertMonster(monster);
	
}

@Override
public Monster selectMon(Monster monster) {
	// TODO Auto-generated method stub
	return md.selectMon(monster);
}

@Override
public void insertBattle(Battle battle) {
	// TODO Auto-generated method stub
	md.insertBattle(battle);
}

@Override
public Battle selectBattle(Battle battle) {
	// TODO Auto-generated method stub
	return md.selectBattle(battle);
}

@Override
public void updateBattle(Battle battle) {
	// TODO Auto-generated method stub
	md.updateBattle(battle);
}

@Override
public Monster selectMonNo(int monsterNo) {
	// TODO Auto-generated method stub
	return md.selectMonNo(monsterNo);
}

@Override
public Battle selectBattleNo(int battleNo) {
	// TODO Auto-generated method stub
	return md.selectBattleNo(battleNo);
}

@Override
public int dmg(int sheeld, int atk) {
	int dmg= atk-sheeld;
	if(dmg<=0)
	{
		dmg=0;
		return dmg;
	}
	else
	{
		return dmg;
	}
	
}

@Override
public int reflex(int dmg) {
	// TODO Auto-generated method stub
	double dmgD=(double)0.7*dmg;
	int answer= (int) Math.round(dmgD);
	return answer;
}

@Override
public int updateweapon(Player player) {
	// TODO Auto-generated method stub
	return md.updateweapon(player);
}

@Override
public void releaseItem(Player player) {
	if(player.getItemNo()==1) {
		double atk=(player.getAtk()/1.05);
		player.setAtk((int)atk);
	}
	if(player.getItemNo()==4) {
		double atk=(player.getAtk()/1.1);
		player.setAtk((int)atk);
	}
	if(player.getItemNo()==2) {
		double atk=(player.getAtk()/1.1);
		player.setAtk((int)atk);
		double atkspeed=(player.getAtkSpeed()*1.05);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==5) {
		double atk=(player.getAtk()/1.2);
		player.setAtk((int)atk);
		double atkspeed=(player.getAtkSpeed()*1.1);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==3) {
		double atkspeed=(player.getAtkSpeed()/1.05);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==6) {
		double atkspeed=(player.getAtkSpeed()/1.1);
		player.setAtkSpeed((int)atkspeed);
	}
	md.releaseItem(player);
	
}

@Override
public void wearItem(Player player) {
	if(player.getItemNo()==1) {
		double atk=(player.getAtk()*1.05);
		player.setAtk((int)atk);
	}
	if(player.getItemNo()==4) {
		double atk=(player.getAtk()*1.1);
		player.setAtk((int)atk);
	}
	if(player.getItemNo()==2) {
		double atk=(player.getAtk()*1.1);
		player.setAtk((int)atk);
		double atkspeed=(player.getAtkSpeed()/1.05);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==5) {
		double atk=(player.getAtk()*1.2);
		player.setAtk((int)atk);
		double atkspeed=(player.getAtkSpeed()/1.1);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==3) {
		double atkspeed=(player.getAtkSpeed()*1.05);
		player.setAtkSpeed((int)atkspeed);
	}
	if(player.getItemNo()==6) {
		double atkspeed=(player.getAtkSpeed()*1.1);
		player.setAtkSpeed((int)atkspeed);
	}
	md.releaseItem(player);
}

@Override
public void steam(Player player) {
	double atk= player.getAtk()*1.2;
	player.setAtk((int)atk);
	md.releaseItem(player);
}

@Override
public void unsteam(Player player) {
	double atk= player.getAtk()/1.2;
	player.setAtk((int)atk);
	md.releaseItem(player);
	
}

@Override
public void insertRecord(Record record) {
	md.insertRecord(record);
	
}

@Override
public void levelup(Player player) {
	md.levelup(player);
	
}

@Override
public List<Record> selectRecord() {
	// TODO Auto-generated method stub
	return md.selectRecord();
}

@Override
public void guard(Player player) {
	double sheeld= player.getSheeld()*1.3;
	player.setSheeld((int)sheeld);
	md.setskill(player);
}

@Override
public void unguard(Player player) {
	double sheeld= player.getSheeld()/1.3;
	player.setSheeld((int)sheeld);
	md.setskill(player);
	
}

@Override
public void invincible(Player player) {
	int sheeld= player.getSheeld()+100000000;
	player.setSheeld(sheeld);
	md.setskill(player);
	
}

@Override
public void uninvincible(Player player) {
	int sheeld= player.getSheeld()-100000000;
	player.setSheeld(sheeld);
	md.setskill(player);
}

@Override
public void anger(Player player) {
	double atk=player.getAtk()*1.5;
	player.setAtk((int)atk);
	double sheeld= player.getSheeld()*0.9;
	player.setSheeld((int)sheeld);
	md.setskill(player);	
}

@Override
public void unanger(Player player) {
	double atk=player.getAtk()/1.5;
	player.setAtk((int)atk);
	double sheeld= player.getSheeld()/0.9;
	player.setSheeld((int)sheeld);
	md.setskill(player);
	
}

@Override
public void frenzy(Player player) {
	int atk=player.getAtk()*5;
	player.setAtk(atk);
	md.setskill(player);
	
	
}

@Override
public void unfrenzy(Player player) {
	int atk=player.getAtk()/5;
	player.setAtk(atk);
	md.setskill(player);
	
}

@Override
public void elusion(Player player) {
	double dex= player.getDex()*1.3;
	player.setDex((int)dex);
	md.setskill(player);
	
}

@Override
public void unelusion(Player player) {
	double dex= player.getDex()/1.3;
	player.setDex((int)dex);
	md.setskill(player);
}

@Override
public void rapid(Player player) {
	double atkSpeed= player.getAtkSpeed()*5;
	player.setAtkSpeed((int)atkSpeed);
	md.setskill(player);
	
}

@Override
public void unrapid(Player player) {
	double atkSpeed= player.getAtkSpeed()/5;
	player.setAtkSpeed((int)atkSpeed);
	md.setskill(player);
}


}
