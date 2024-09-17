package Zhenghuo.card;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.cards.blue.Loop;
import com.megacrit.cardcrawl.cards.purple.Vault;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
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
import com.megacrit.cardcrawl.monsters.city.Chosen;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.Objects;

public class Tengyue extends AbstractCard {
    public static final String ID = "Tengyue";
    private static final CardStrings cardStrings;

    public Tengyue() {
        super("Tengyue", cardStrings.NAME, "purple/skill/vault", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.ALL);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
      addToBot(new AbstractGameAction() {
          @Override
          public void update() {
              for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                  if(Objects.equals(monster.id, Cultist.ID) || Objects.equals(m.id, Chosen.ID))
                  {
                      if(!m.hasPower(SurroundedPower.POWER_ID)) {
                          addToTop(new EscapeAction(monster));
                      }
                      else{
                          addToTop(new TalkAction(m,"我被夹击了无法逃跑"));
                      }
                  }
              }

              isDone=true;
          }
      });
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }

    }

    public AbstractCard makeCopy() {
        return new Tengyue();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Tengyue");
    }
}