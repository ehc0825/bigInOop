package com.hc.MirrorBT.service;

import java.util.List;

import com.hc.MirrorBT.model.Battle;
import com.hc.MirrorBT.model.Monster;
import com.hc.MirrorBT.model.Player;
import com.hc.MirrorBT.model.Record;

public interface MainService {

	int insert(Player player);

	Player select(String nick);

	Player selectbyNo(int playerNo);

	Monster monsterset(Player player);

	void insertMonster(Monster monster);

	Monster selectMon(Monster monster);

	void insertBattle(Battle battle);

	Battle selectBattle(Battle battle);

	void updateBattle(Battle battle);

	Monster selectMonNo(int monsterNo);

	Battle selectBattleNo(int battleNo);

	int dmg(int sheeld, int atk);
	int reflex(int dmg);

	int updateweapon(Player player);

	void releaseItem(Player player);

	void wearItem(Player player);

	void steam(Player player);

	void unsteam(Player player);

	void insertRecord(Record record);

	void levelup(Player player);

	List<Record> selectRecord();

	void guard(Player player);

	void unguard(Player player);

	void invincible(Player player);

	void uninvincible(Player player);

	void anger(Player player);

	void unanger(Player player);

	void frenzy(Player player);

	void unfrenzy(Player player);

	void elusion(Player player);

	void unelusion(Player player);

	void rapid(Player player);

	void unrapid(Player player);
}
