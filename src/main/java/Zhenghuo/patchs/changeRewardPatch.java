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
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import static Zhenghuo.actions.ChangePlayerAction.ChangePlayer;
import static Zhenghuo.modcore.ExampleMod.NowPlayer;
import static Zhenghuo.modcore.ExampleMod.morengcharacter;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


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
             __instance.isDone=true;
             return SpireReturn.Return();
         }
         else {
             return SpireReturn.Continue();
         }
        }
    }
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.screens.custom.CustomModeScreen",
            method = "initializeCharacters"
    )
    public static class InitizePatch {
        @SpireInsertPatch(
                rloc =0
        )
        public static SpireReturn Insert(CustomModeScreen __instance) {
            if(morengcharacter!=null&&player!=null){
                player.chosenClass = morengcharacter;
            }

            return SpireReturn.Continue();
        }
    }
}



