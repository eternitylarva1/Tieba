package Zhenghuo.card.purple;

import Zhenghuo.actions.DiscoveryAction2;
import Zhenghuo.utils.Invoker;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.Slot;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.BustedCrown;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class Tongxiaowanwu extends AbstractCard {
    public static final String ID = "WheelKick";
    private static final CardStrings cardStrings;

    public Tongxiaowanwu() {
        super("WheelKick", cardStrings.NAME, "purple/attack/wheel_kick", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = false;
        this.baseMagicNumber=this.magicNumber=1;
        this.baseDamage = 15;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    this.addToBot(new DiscoveryAction2());

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }

    }

    public AbstractCard makeCopy() {
        return new Tongxiaowanwu();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("WheelKick");
    }
}
