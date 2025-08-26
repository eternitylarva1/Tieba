//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.card.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SwordBoomerang extends AbstractCard {
    public static final String ID = "Sword Boomerang";
    private static final CardStrings cardStrings;

    public SwordBoomerang() {
        super("Sword Boomerang", cardStrings.NAME, "red/attack/sword_boomerang", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.returnToHand=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    this.isDone=true;
                    costForTurn=cost;
                }
            });
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

    public AbstractCard makeCopy() {
        return new SwordBoomerang();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Sword Boomerang");
    }
}
