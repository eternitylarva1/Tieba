//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.powers;

import Zhenghuo.card.purple.LessonLearned;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class StudyPower extends AbstractPower {
    public static final String POWER_ID = "Study";
    private static final PowerStrings powerStrings;

    public StudyPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Study";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("draw");
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
    }

    public void atEndOfTurn(boolean playerTurn) {
        this.addToBot(new MakeTempCardInDrawPileAction(new LessonLearned(), this.amount, true, true));
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1].replace("洞见","勤学精进");
        } else {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2].replace("洞见","勤学精进");
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Study");
    }
}
