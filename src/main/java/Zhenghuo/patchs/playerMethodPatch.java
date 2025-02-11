package Zhenghuo.patchs;

import Zhenghuo.otherplayer.AbstractOtherPlayer;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.utils.Calculate;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.ui.controls.EUITextBoxInput;

import static Zhenghuo.modcore.ExampleMod.UITorenders;


public class playerMethodPatch {
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "renderPlayerBattleUi"
    )
    public static class RenderPatch {
        public RenderPatch() {
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer _instance, SpriteBatch sb) {

            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (OtherPlayerHelper.hasMinions(AbstractDungeon.player)) {
                       OtherPlayerHelper.getMinions().render(sb);
                    }



            }
        }
    }  @SpirePatch(
            clz = AbstractMonster.class,
            method = "render"
    )
    public static class RenderMonsterPatch {
        public RenderMonsterPatch() {
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractMonster monster, SpriteBatch sb) {

            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    MonsterAddFieldsPatch.f_Inputers.get(monster).render(sb);
                    Object[] MonsterQuestion=MonsterAddFieldsPatch.f_questions.get(monster);
                    FontHelper.renderFont(sb,FontHelper.topPanelAmountFont, Calculate.convertArrayToQuestion(MonsterQuestion),monster.drawX-50,monster.drawY-monster.hb_h*0.2f, Color.WHITE);




            }
        }
    }
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "update"
    )
    public static class updatePatch {
        public updatePatch() {
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer _instance) {

            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:

                    for (EUITextBoxInput uiTorender : UITorenders) {
                        uiTorender.update();

                    }

            }
        }
    }

}
