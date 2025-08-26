package Zhenghuo.patchs;

import ChatterMod.actions.RecordAndPlaybackAction;
import Zhenghuo.modcore.ExampleMod;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.powers.StudyPower1;
import Zhenghuo.powers.cantfleetPower;
import Zhenghuo.utils.Calculate;
import Zhenghuo.utils.ScreenDarkener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Vigilance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import extendedui.ui.controls.EUITextBoxInput;

import static Zhenghuo.modcore.ExampleMod.Tips;
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
        public static <K, V> void Postfix(AbstractPlayer _instance, SpriteBatch sb) {

            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (OtherPlayerHelper.hasMinions(AbstractDungeon.player)) {
                       OtherPlayerHelper.getMinions().render(sb);
                    }
                    for (ScreenDarkener screendarkener : ExampleMod.screendarkeners) {
                        screendarkener.render(sb);
                    }
             

                    FontHelper.renderFont(sb, FontHelper.topPanelAmountFont,Tips,_instance.drawX-50,_instance.drawY+_instance.hb_h*0.2f, Color.WHITE);

/*
                    for (Map.Entry<String, Texture> entry : cache.entrySet()) {
                         String key = (String) entry.getKey();
                        Texture value = (Texture) entry.getValue();/*
                        sb.draw(value, 0.0F, 0.0F, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());

                        // 处理 key 和 value
                    }*/


            }
        }
    }
    @SpirePatch(
            clz =AbstractPlayer.class ,
            method = "updateEscapeAnimation"
    )
    public static class EscapePatch {
        public EscapePatch() {
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer _instance) {

            if (_instance.hasPower(cantfleetPower.POWER_ID)&&_instance.escapeTimer>0.0F) {
                AbstractDungeon.effectList.add(new ThoughtBubble(_instance.dialogX, _instance.dialogY, 3.0F, "我岿然不动，无法逃跑", true));
                AbstractDungeon.player.showHealthBar();
                AbstractDungeon.overlayMenu.endTurnButton.enable();
                _instance.flipHorizontal = false;
                _instance.isEscaping = false;
                _instance.escapeTimer = 0.0F;

            }
                    
            
        }
    }




    @SpirePatch(
            clz = AbstractMonster.class,
            method = "render"
    )
    public static class RenderMonsterPatch {
        public RenderMonsterPatch() {
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractMonster monster, SpriteBatch sb) {
            if (!monster.hasPower(StudyPower1.POWER_ID)) {
                return;
            }
            switch (AbstractDungeon.getCurrRoom().phase) {

                case COMBAT:
                    MonsterAddFieldsPatch.f_Inputers.get(monster).render(sb);
                    Object[] MonsterQuestion=MonsterAddFieldsPatch.f_questions.get(monster);
                    FontHelper.renderFont(sb,FontHelper.charTitleFont, Calculate.convertArrayToQuestion(MonsterQuestion),monster.drawX-monster.hb_w/2, monster.drawY+monster.hb_h+50*Settings.scale, Color.WHITE);
/*
                if(!ExampleMod.StartRecord){
                    return;
                }*/



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
