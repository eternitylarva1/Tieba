//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CombustPower extends AbstractPower {
    public static final String POWER_ID = "Combust";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private int hpLoss;

    public CombustPower(AbstractCreature owner, int hpLoss, int damageAmount) {
        this.name = NAME;
        this.ID = "Combust";
        this.owner = owner;
        this.amount = damageAmount;
        this.hpLoss = hpLoss;
        this.updateDescription();
        this.loadRegion("combust");
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.hpLoss, AttackEffect.FIRE));
            this.addToBot(new MakeTempCardInHandAction(new Inflame()));}

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        ++this.hpLoss;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.hpLoss + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Combust");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
