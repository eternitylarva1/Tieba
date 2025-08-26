//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.card.red;

import Zhenghuo.powers.cantfleetPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class kuiranbudong extends AbstractCard {
    public static final String ID = "Impervious";
    private static final CardStrings cardStrings;

    public kuiranbudong() {
        super("Impervious", cardStrings.NAME, "red/skill/impervious", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 30;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p,p,new cantfleetPower(p,-1)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(10);
        }

    }

    public AbstractCard makeCopy() {
        return new kuiranbudong();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Impervious");
    }
}
