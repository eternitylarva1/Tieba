package Zhenghuo.card.green;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shengdongjixi extends AbstractCard {
    public static final String ID = "Distraction";
    private static final CardStrings cardStrings;

    public Shengdongjixi() {
        super("Distraction", cardStrings.NAME, "green/attack/unload", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
addToBot(new AbstractGameAction() {
    @Override
    public void update() {

        isDone=true;
    }
});
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }

    }

    public AbstractCard makeCopy() {
        return new Shengdongjixi();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Distraction");
    }
}
