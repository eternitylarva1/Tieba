package Zhenghuo.actions;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import Zhenghuo.card.AugrmentAttack;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

import static Zhenghuo.utils.Calculate.*;

public class DiscoveryAction1 extends AbstractGameAction {
    private boolean retrieveCard = false;
    private boolean returnColorless = false;
    private AbstractCard.CardType cardType = null;

    public DiscoveryAction1() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
    }

    public DiscoveryAction1(AbstractCard.CardType type, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.cardType = type;
    }

    public DiscoveryAction1(boolean colorless, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.returnColorless = colorless;
    }

    public void update() {
        ArrayList generatedCards;
        Object[] question =generateMathQuestion();
//生成三个AugrmentAttack对象，参数分别是qustion的答案，和两个干扰项的答案,
        Object[] distractors = generateDistractors(question);
        generatedCards=new ArrayList<AbstractCard>();
        //用三个变量把这三个对象储存起来
        AugrmentAttack correctAnswer = new AugrmentAttack((Integer.toString( (Integer) question[2])));
        AugrmentAttack distractor1 = new AugrmentAttack(Integer.toString((Integer) distractors[0]));
        AugrmentAttack distractor2 = new AugrmentAttack(Integer.toString((Integer) distractors[1]));
        generatedCards.add(correctAnswer);
        generatedCards.add(distractor1);
        generatedCards.add(distractor2);




        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, convertArrayToQuestion(question), this.cardType != null);
            System.out.println(convertArrayToQuestion(question));
            this.tickDuration();
            System.out.println("干扰项1："+distractor1.rawDescription);
            System.out.println("干扰项2："+distractor2.rawDescription);
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                //如果选择的卡牌是correctAnswer
                    System.out.println(correctAnswer.rawDescription);
                    System.out.println(AbstractDungeon.cardRewardScreen.discoveryCard.rawDescription);

                    if (Objects.equals(AbstractDungeon.cardRewardScreen.discoveryCard.rawDescription, correctAnswer.rawDescription)) {
                        AbstractDungeon.actionManager.addToBottom(new CalculatedGambleAction(false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(amount));
System.out.println("correctAnswer");
                    }
                    else {
                        AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player,AbstractDungeon.player,99,true));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(amount));
System.out.println("wrongAnswer");
                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateColorlessCardChoices() {
        ArrayList<AbstractCard> derp = new ArrayList();

        while(derp.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
            Iterator var4 = derp.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                derp.add(tmp.makeCopy());
            }
        }

        return derp;
    }

    private ArrayList<AbstractCard> generateCardChoices(AbstractCard.CardType type) {
        ArrayList<AbstractCard> derp = new ArrayList();

        while(derp.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = null;
            if (type == null) {
                tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
            } else {
                tmp = AbstractDungeon.returnTrulyRandomCardInCombat(type);
            }

            Iterator var5 = derp.iterator();

            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                derp.add(tmp.makeCopy());
            }
        }

        return derp;
    }
}

