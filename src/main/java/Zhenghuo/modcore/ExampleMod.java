package Zhenghuo.modcore;

import Zhenghuo.card.*;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.relics.StrongCharacter;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Zhenghuo.actions.ChangePlayerAction.ChangePlayer;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


@SpireInitializer
public class ExampleMod implements PostDungeonInitializeSubscriber,OnStartBattleSubscriber, PostBattleSubscriber,CustomSavable<String>,EditCardsSubscriber, EditStringsSubscriber , EditRelicsSubscriber { // 实现接口
public static String NowPlayer=null;
    public ExampleMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addSaveField("Zhenghuo", this);
    }

    public static void initialize() {
        new ExampleMod();
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数

    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Soul_P());
        BaseMod.addCard(new Soul_G());
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "ZhenghuoResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。

        BaseMod.loadCustomStringsFile(RelicStrings.class, "ZhenghuoResources/localization/" + lang + "/relics.json");// 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
            }



    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new StrongCharacter(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物

    }

    @Override
    public String onSave() {
        System.out.println("正在保存");/*
        ChangePlayer(NowPlayer);
        for (RewardItem reward : getCurrRoom().rewards) {
            System.out.println("替换中");
            if (reward.type == RewardItem.RewardType.CARD) {
                System.out.println("已执行替换");
                reward.cards = AbstractDungeon.getRewardCards();


            }
        }*/
            return NowPlayer;

    }
    @Override
    public void onLoad(String s) {

        NowPlayer=s;
        System.out.println("成功加载");
        ChangePlayer(NowPlayer);


    }

public static void ChangeFirstPlayer()
{

}
    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if(NowPlayer==null)
        {
            NowPlayer=player.getClass().getSimpleName();
        }
        ChangePlayer(NowPlayer);


    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        OtherPlayerHelper.clearMinions(player);
    }

    @Override
    public void receivePostDungeonInitialize() {
        if(!Settings.isEndless){
            NowPlayer = null;
        }
    }
}
