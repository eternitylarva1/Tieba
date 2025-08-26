//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.powers;

import Zhenghuo.card.purple.LessonLearned;
import Zhenghuo.patchs.MonsterAddFieldsPatch;
import Zhenghuo.utils.Calculate;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRM;
import extendedui.ui.controls.EUITextBoxInput;
import extendedui.ui.hitboxes.EUIHitbox;

import static Zhenghuo.modcore.ExampleMod.UITorenders;
import static extendedui.EUIGameUtils.scale;

public class StudyPower1 extends AbstractPower {
    public static final String POWER_ID = "Study";
    private static final PowerStrings powerStrings;

    public StudyPower1(AbstractCreature owner, int amount) {
        this.name = "勤学精进";
        this.ID = "Study";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("draw");
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
    }

    public void atEndOfTurn(boolean playerTurn) {
        if (!(this.owner instanceof AbstractMonster)){
            return;
        }
System.out.println(MonsterAddFieldsPatch.f_Inputers.get(this.owner).getBuffer().toString());
        System.out.println(MonsterAddFieldsPatch.f_questions.get(this.owner)[2]);
   if (MonsterAddFieldsPatch.f_Inputers.get(this.owner).getBuffer().toString().equals(MonsterAddFieldsPatch.f_questions.get(this.owner)[2].toString())){
       AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner,this.owner,this.owner.maxHealth/2));
       final EUITextBoxInput descriptionInput;
       descriptionInput=(EUITextBoxInput) new EUITextBoxInput(EUIRM.images.rectangularButton.texture(),
               new EUIHitbox(this.owner.drawX-30, this.owner.drawY+this.owner.hb_h*1.5f, scale(100), scale(40)).setIsPopupCompatible(true))
               .setHeader(FontHelper.topPanelAmountFont, 0.8f, Settings.GOLD_COLOR, "请输入答案")
               .setHeaderSpacing(1f)
               .setColors(Color.GRAY, Settings.CREAM_COLOR)
               .setAlignment(0.5f, 0.1f)
               .setFont(FontHelper.cardDescFont_N, 0.8f)
               .setBackgroundTexture(EUIRM.images.rectangularButton.texture());
       UITorenders.add(descriptionInput);
       MonsterAddFieldsPatch.f_Inputers.set(this.owner,descriptionInput);
       MonsterAddFieldsPatch.f_questions.set(this.owner, Calculate.generateMathQuestion());
   }
        UITorenders.remove(0);
    }

    public void updateDescription() {
  this.description="回合结束时，如果你的回答是算式的正确答案，则失去25%的生命";
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Study");
    }
}
