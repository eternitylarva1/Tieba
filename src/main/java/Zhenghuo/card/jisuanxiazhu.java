package Zhenghuo.card;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Discovery;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.monsters.city.Chosen;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.Objects;

public class jisuanxiazhu extends AbstractCard {
    public static final String ID = "Calculated Gamble";
    private static final CardStrings cardStrings;

    public jisuanxiazhu() {
        super("Calculated Gamble", cardStrings.NAME, "green/skill/calculated_gamble", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p,p,100,true));


    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
     this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
     initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new jisuanxiazhu();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Calculated Gamble");
    }
}
