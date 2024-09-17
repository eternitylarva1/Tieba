package Zhenghuo.card;

import basemod.devcommands.history.History;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.blue.Darkness;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.screens.stats.RunData;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static basemod.devcommands.history.History.*;

public class ThunderClap extends AbstractCard {
    public static final String ID = "Thunderclap";
    private static final CardStrings cardStrings;

    public ThunderClap() {
        super("Thunderclap", cardStrings.NAME, "red/attack/thunder_clap", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.isMultiDamage = true;
        this.baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (!mo.isDeadOrEscaped()) {

                this.addToBot(new ChannelAction(new Lightning()));
            }
        }


    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }

    }

    public AbstractCard makeCopy() {
        return new ThunderClap();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Thunderclap");
    }

}

