package com.hc.MirrorBT.model;

import lombok.Data;
import oracle.sql.DATE;
@Data
public class Record {
	private int recordNo;
	private int stage;
	private String nick;
	private DATE endTime;
}
