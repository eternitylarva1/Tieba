package Zhenghuo.patchs;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

import static Zhenghuo.actions.ChangePlayerAction.ChangePlayer;
import static Zhenghuo.modcore.ExampleMod.NowPlayer;


public class changeRewardPatch {

//    private static final Logger logger = LogManager.getLogger(BronzeOrbPatch.class.getName());

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.screens.CombatRewardScreen",
            method = "setupItemReward"
    )
    public static class RewardPatch {
        @SpireInsertPatch(
                loc = 75
        )
        public static SpireReturn Insert(CombatRewardScreen __instance) {
            ChangePlayer(NowPlayer);
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.actions.common.EscapeAction",
            method = "update"
    )
    public static class updatePatch {
        @SpireInsertPatch(
                rloc =2
        )
        public static SpireReturn Insert(EscapeAction __instance) {
         if(__instance.source.hasPower(SurroundedPower.POWER_ID)) {
             AbstractDungeon.actionManager.addToBottom(new TalkAction(__instance.source,"我被包围了，无法逃跑"));
             return SpireReturn.Return();
         }
         else {
             return SpireReturn.Continue();
         }
        }
    }
}



