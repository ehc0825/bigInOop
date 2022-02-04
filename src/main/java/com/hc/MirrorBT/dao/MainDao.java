package com.hc.MirrorBT.dao;

import java.util.List;

import com.hc.MirrorBT.model.Battle;
import com.hc.MirrorBT.model.Monster;
import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Record;

public interface MainDao {

	int insert(Player player);

	Player select(String nick);

	Player selectbyNo(int playerNo);

	void insertMonster(Monster monster);

	Monster selectMon(Monster monster);

	void insertBattle(Battle battle);

	Battle selectBattle(Battle battle);

	void updateBattle(Battle battle);

	Monster selectMonNo(int monsterNo);

	Battle selectBattleNo(int battleNo);

	int updateweapon(Player player);

	void releaseItem(Player player);

	void insertRecord(Record record);

	void levelup(Player player);

	List<Record> selectRecord();

	void setskill(Player player);

}
