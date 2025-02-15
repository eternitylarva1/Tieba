package Zhenghuo.patchs;

import Zhenghuo.modcore.ExampleMod;
import Zhenghuo.otherplayer.AbstractOtherPlayer;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.utils.Calculate;
import Zhenghuo.utils.ScreenDarkener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.red.Disarm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.ui.controls.EUITextBoxInput;

import java.util.Map;

import static Zhenghuo.modcore.ExampleMod.UITorenders;
import static Zhenghuo.utils.TextureCache.cache;
import static com.badlogic.gdx.graphics.GL20.*;


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
/*
                    for (Map.Entry<String, Texture> entry : cache.entrySet()) {
                         String key = (String) entry.getKey();
                        Texture value = (Texture) entry.getValue();/*
                        sb.draw(value, 0.0F, 0.0F, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());

                        // 处理 key 和 value
                    }*/


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
                case COMBAT:/*
                    MonsterAddFieldsPatch.f_Inputers.get(monster).render(sb);
                    Object[] MonsterQuestion=MonsterAddFieldsPatch.f_questions.get(monster);
                    FontHelper.renderFont(sb,FontHelper.topPanelAmountFont, Calculate.convertArrayToQuestion(MonsterQuestion),monster.drawX-50,monster.drawY-monster.hb_h*0.2f, Color.WHITE);
*/



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
