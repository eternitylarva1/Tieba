package Zhenghuo.card;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import Zhenghuo.actions.ChangePlayerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

public class Reprogram extends AbstractCard {
    public static final String ID = "Chongbiancheng";
    private static final CardStrings cardStrings;
    public String player="";

    public Reprogram() {
        super(ID, cardStrings.NAME, "blue/skill/reprogram", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }
    public Reprogram(String player) {
        super("Reprogram", cardStrings.NAME, "blue/skill/reprogram", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        switch (player){
            case"Ironclad":
                //addToBot(new ChangePlayerAction("Ironclad"));
                this.rawDescription="变成铁甲战士";
                break;
            case"TheSilent":
                //addToBot(new ChangePlayerAction("TheSilent"));
                this.rawDescription="变成静默猎手";
                break;
        }
        initializeDescription();
        this.player=player;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList();
        stanceChoices.add(new Reprogram("Ironclad"));
        stanceChoices.add(new Reprogram("TheSilent"));
        this.addToBot(new ChooseOneAction(stanceChoices));
    }


    public AbstractCard makeCopy() {
        return new Reprogram();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }
    public void onChoseThisOption() {
addToBot(new ChangePlayerAction(this.player));

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}
