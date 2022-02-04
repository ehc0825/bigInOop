package com.hc.MirrorBT.model;

import lombok.Data;
import oracle.sql.DATE;
@Data
public class Player {
	private int playerNo;
	private String nick;
	private String tribe;
    private int lv;
    private int hp;
    private int mp;
    private int atk;
    private int atkSpeed;
    private int sheeld;
    private int dex;
    private DATE startTime;
    private String shopCheck;
    private int money;
    private int itemNo;
    //플레이어전투중 hpmp
    private int nowHp;
    private int nowMp;
}
