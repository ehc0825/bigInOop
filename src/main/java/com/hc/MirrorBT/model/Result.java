package com.hc.MirrorBT.model;

import lombok.Data;

@Data
public class Result {
 private Player player;
 private Monster monster;
 private Battle battle;
 private String msg;
}
