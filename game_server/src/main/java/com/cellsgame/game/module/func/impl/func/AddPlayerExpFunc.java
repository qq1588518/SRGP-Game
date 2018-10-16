package com.cellsgame.game.module.func.impl.func;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;

import com.cellsgame.game.cache.Enums;
import com.cellsgame.game.core.excption.LogicException;
import com.cellsgame.game.core.message.CMD;
import com.cellsgame.game.module.func.CheckRec;
import com.cellsgame.game.module.func.FuncParam;
import com.cellsgame.game.module.func.IChecker;
import com.cellsgame.game.module.func.SyncFunc;
import com.cellsgame.game.module.func.cons.ICheckerType;
import com.cellsgame.game.module.func.cons.PrizeConstant;
import com.cellsgame.game.module.func.formula.FormulaType;
import com.cellsgame.game.module.player.bo.PlayerBO;
import com.cellsgame.game.module.player.vo.PlayerVO;

public class AddPlayerExpFunc extends SyncFunc {

    @Resource
    private PlayerBO playerBO;

    @Override
    public Object exec(Map<?, ?> parent, Map<?, ?> prizeMap, CMD cmd, PlayerVO player, FuncParam param,int execNum) throws LogicException {
        FormulaType formulaType = Enums.get(FormulaType.class, param.getParam2());
        long changeVal = param.getValue();
        if(formulaType != null) {
            changeVal = formulaType.getFormula().getValue(player);
        }
        changeVal = changeVal * execNum;
        parent = playerBO.changeExp(parent, player, changeVal, cmd);
        if (prizeMap != null) PrizeConstant.addPlayerExp(prizeMap, changeVal);
        return parent;
    }

    @Override
    public IChecker getParamChecker() {
        return ICheckerType.PlayerExp.getChecker();
    }


    @Override
    public Collection<CheckRec<?>> record(PlayerVO player, FuncParam param) {
        return null;
    }

}
