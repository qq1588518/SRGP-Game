package com.cellsgame.game.module.activity.cond.impl;

import com.cellsgame.game.core.event.EvtType;
import com.cellsgame.game.core.event.GameEvent;
import com.cellsgame.game.module.activity.cond.ACondProcess;
import com.cellsgame.game.module.activity.csv.ActivityCond;
import com.cellsgame.game.module.activity.vo.ActivityCondRecVO;
import com.cellsgame.game.module.activity.vo.ActivityVO;
import com.cellsgame.game.module.pay.cons.EvtPay;
import com.cellsgame.game.module.player.vo.PlayerVO;

import java.util.Map;


public class PayTimesCondProcess extends ACondProcess {

	@Override
	public EvtType[] getConcernType() {
		return new EvtType[]{EvtPay.Pay};
	}

	@Override
	public boolean recordCondData(Map<?, ?> parent, ActivityVO act, ActivityCond cond,
			ActivityCondRecVO condRecVO, PlayerVO player, GameEvent event) {
		condRecVO.setFinishData(Math.min(condRecVO.getFinishData() + 1, cond.getValue()));
		return true;
	}

}
