//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ThisTurnBlockPower extends AbstractPower {
    public static final String POWER_ID = "Next Turn Block";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public ThisTurnBlockPower(AbstractCreature owner, int armorAmt, String newName) {
        this.name = "警惕";
        this.ID = "Next Turn Block";
        this.owner = owner;
        this.amount = armorAmt;
        this.updateDescription();
        this.loadRegion("defenseNext");
    }

    public ThisTurnBlockPower(AbstractCreature owner, int armorAmt) {
        this(owner, armorAmt, NAME);
    }

    public void updateDescription() {
        this.description = "本回合每次受到伤害时,获得"+this.amount+"点格挡";
    }

    public void atStartOfTurn() {
        this.flash();
        AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.owner.hb.cX, this.owner.hb.cY, AttackEffect.SHIELD));
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Next Turn Block"));

    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToTop(new GainBlockAction(owner,this.amount));
        }

        return damageAmount;
    }
    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Next Turn Block");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
