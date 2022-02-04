package com.hc.MirrorBT.model;

import lombok.Data;

@Data
public class Monster {
	private int monsterNo;
	private String monsterNAME;
	private String tribe;
	private int lv;
	private int hp;
	private int mp;
	private int atk;
	private int atkSpeed;
	private int sheeld;
	private int dex;
	 //몬스터전투중 hpmp
    private int nowHp;
    private int nowMp;
}
