package Zhenghuo.card;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.purple.Wish;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

public class Xuyuan extends AbstractCard {
    public static final String ID = "Xuyuan";
    private static final CardStrings cardStrings;

    public Xuyuan() {
        super("Wish", cardStrings.NAME, "purple/skill/wish", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.baseDamage = 3;
        this.baseMagicNumber = 25;
        this.magicNumber = 25;
        this.baseBlock = 6;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(1));
        try {
            Desktop.getDesktop().browse(new URI("https://www.bilibili.com/video/BV1Mf4y1P7G9/"));
        } catch (Exception e) {
            e.printStackTrace();
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,AbstractDungeon.player.dialogY,"我没有权限打开浏览器",true));
        }
    }

    public void applyPowers() {
    }

    public void calculateCardDamage(AbstractMonster mo) {
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(5);
            this.upgradeBlock(2);
        }

    }

    public AbstractCard makeCopy() {
        return new Xuyuan();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Xuyuan");
    }
}
