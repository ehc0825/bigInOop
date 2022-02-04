package com.hc.MirrorBT.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hc.MirrorBT.model.Battle;
import com.hc.MirrorBT.model.Monster;
import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Record;

@Repository
public class MainDaoImpl implements MainDao {
@Autowired
private SqlSessionTemplate sst;

@Override
public int insert(Player player) {
	// TODO Auto-generated method stub
	return sst.insert("playerns.insert",player);
}

@Override
public Player select(String nick) {
	// TODO Auto-generated method stub
	return sst.selectOne("playerns.select",nick);
}

@Override
public Player selectbyNo(int playerNo) {
	// TODO Auto-generated method stub
	return sst.selectOne("playerns.selectbyNo",playerNo);
}

@Override
public void insertMonster(Monster monster) {
	sst.insert("monsterns.insertMonster",monster);
	
}

@Override
public Monster selectMon(Monster monster) {
	// TODO Auto-generated method stub
	return sst.selectOne("monsterns.selectMon",monster);
}

@Override
public void insertBattle(Battle battle) {
	sst.insert("battlens.insertBattle",battle);
}

@Override
public Battle selectBattle(Battle battle) {
	// TODO Auto-generated method stub
	return sst.selectOne("battlens.selectBattle",battle);
}

@Override
public void updateBattle(Battle battle) {
	// TODO Auto-generated method stub
	sst.update("battlens.updateBattle",battle);
}

@Override
public Monster selectMonNo(int monsterNo) {
	// TODO Auto-generated method stub
	return sst.selectOne("monsterns.selectMonNo",monsterNo);
}

@Override
public Battle selectBattleNo(int battleNo) {
	// TODO Auto-generated method stub
	return sst.selectOne("battlens.selectBattleNo",battleNo);
}

@Override
public int updateweapon(Player player) {
	// TODO Auto-generated method stub
	return sst.update("playerns.updateweapon",player);
}

@Override
public void releaseItem(Player player) {
	// TODO Auto-generated method stub
	sst.update("playerns.releaseItem",player);
}

@Override
public void insertRecord(Record record) {
	sst.insert("recordns.insertRecord",record);
	
}

@Override
public void levelup(Player player) {
	sst.update("playerns.levelup",player);
	
}

@Override
public List<Record> selectRecord() {
	// TODO Auto-generated method stub
	return sst.selectList("recordns.selectRecord");
}

@Override
public void setskill(Player player) {
	sst.update("playerns.setskill",player);
	
}
}
