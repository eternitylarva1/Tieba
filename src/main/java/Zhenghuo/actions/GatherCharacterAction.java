package Zhenghuo.actions;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import Zhenghuo.card.CharacterCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ForeignInfluenceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.EmptyRoom;

import java.util.*;
import java.util.stream.Collectors;

public class GatherCharacterAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean endTurn;
    public static int numDiscarded;
    private static final float DURATION;

    public GatherCharacterAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(target, source, amount, isRandom, false);
    }

    public GatherCharacterAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn) {
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
    }

    public void update() {
        AbstractCard c;
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }



            if (!this.isRandom) {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open("选择需要组合的牌", 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    this.tickDuration();
                    return;
                }

                numDiscarded = this.amount;
                if (this.p.hand.size() > this.amount) {
                    AbstractDungeon.handCardSelectScreen.open("选择需要组合的牌", this.amount, true,true);
                }
                else{
                    AbstractDungeon.handCardSelectScreen.open("选择需要组合的牌", this.p.hand.size(), true,true);


                }
                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();
            ArrayList<Character> ChList = new ArrayList<>();
            while(var4.hasNext()) {
                c = (AbstractCard)var4.next();

                this.p.hand.moveToExhaustPile(c);
                if(!(c.name =="通配符")){
                    for (char ch : c.name.toCharArray()) {
                        ChList.add(ch);
                    }
                }
                else{
                    System.out.println("检测到名字为通配符，改为*");
                    ChList.add("*".charAt(0));
                }
               }

                List<AbstractCard> result =result(ChList);
                if(!result.isEmpty()){

                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(result.get(AbstractDungeon.cardRandomRng.random(0,result.size()-1))));
                }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
public List<AbstractCard> result (List<Character> charList){
        return CardLibrary.getAllCards().stream()
                .filter(obj -> {
                    String name = obj.name;

                    // 将 name 转换为字符集
                    List<Character> nameCharList = name.chars()
                            .mapToObj(c -> (char) c)
                            .collect(Collectors.toList());

                    // 复制一份字符集以供匹配
                    List<Character> charListCopy = charList.stream()
                            .collect(Collectors.toList());

                    // 检查字符串中的字符是否能在字符集中找到对应项或被通配符替代
                    for (char c : nameCharList) {
                        if (charListCopy.contains(c)) {
                            charListCopy.remove((Character) c);
                        } else if (charListCopy.contains('*')) {
                            charListCopy.remove((Character) '*');
                        } else {
                            return false; // 无法匹配字符时，过滤掉这个字符串
                        }
                    }

                    // 最终，charListCopy 应该只剩下未使用的字符
                    return charListCopy.isEmpty();
                })
                .collect(Collectors.toList());
}

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}

