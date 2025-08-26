//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.card.purple;

import Zhenghuo.patchs.MonsterAddFieldsPatch;
import Zhenghuo.powers.StudyPower1;
import Zhenghuo.utils.Calculate;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.LessonLearnedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIRM;
import extendedui.ui.controls.EUITextBoxInput;
import extendedui.ui.hitboxes.EUIHitbox;

import static Zhenghuo.modcore.ExampleMod.UITorenders;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom;
import static extendedui.ui.EUIBase.scale;


public class LessonLearned extends AbstractCard {
    public static final String ID = "LessonLearned";
    private static final CardStrings cardStrings;

    public LessonLearned() {
        super("LessonLearned", cardStrings.NAME, "purple/attack/lessons_learned", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LessonLearnedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        this.addToBot(new ApplyPowerAction(m,p,new StudyPower1(m,-1)));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone=true;
                for (AbstractMonster monster : getCurrRoom().monsters.monsters) {
                    final EUITextBoxInput descriptionInput;
                    descriptionInput=(EUITextBoxInput) new EUITextBoxInput(EUIRM.images.rectangularButton.texture(),
                            new EUIHitbox(monster.drawX-monster.hb_w/2, monster.drawY+monster.hb_h+50*Settings.scale, monster.hb_w, scale(100)).setIsPopupCompatible(true))
                            .setHeader(FontHelper.topPanelAmountFont, 0.5f*Settings.scale, Settings.GOLD_COLOR, "请输入答案")
                            .setHeaderSpacing(1f)
                            .setColors(Color.GRAY, Settings.CREAM_COLOR)
                            .setAlignment(0.5f, 0.1f)
                            .setFont(FontHelper.cardDescFont_N, 0.5f*Settings.scale)
                            .setBackgroundTexture(EUIRM.images.rectangularButton.texture());
                    UITorenders.add(descriptionInput);
                    MonsterAddFieldsPatch.f_Inputers.set(monster,descriptionInput);
                    MonsterAddFieldsPatch.f_questions.set(monster, Calculate.generateMathQuestion());
                }
            }
        });
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }

    }

    public AbstractCard makeCopy() {
        return new LessonLearned();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LessonLearned");
    }
}
