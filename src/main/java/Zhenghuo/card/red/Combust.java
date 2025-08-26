//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.card.red;

import Zhenghuo.powers.CombustPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Combust extends AbstractCard {
    public static final String ID = "Combust";
    private static final CardStrings cardStrings;

    public Combust() {
        super("Combust", cardStrings.NAME, "red/power/combust", 1, cardStrings.DESCRIPTION, CardType.POWER, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CombustPower(p, 1, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }

    }

    public AbstractCard makeCopy() {
        return new Combust();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Combust");
    }
}
