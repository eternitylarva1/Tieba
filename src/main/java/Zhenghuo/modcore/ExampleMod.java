package Zhenghuo.modcore;

import Zhenghuo.card.*;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.relics.StrongCharacter;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.nio.charset.StandardCharsets;

import static Zhenghuo.actions.ChangePlayerAction.ChangePlayer;
import static com.megacrit.cardcrawl.core.Settings.language;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


@SpireInitializer
public class ExampleMod implements EditKeywordsSubscriber,PostDungeonInitializeSubscriber,OnStartBattleSubscriber, PostBattleSubscriber,CustomSavable<String>,EditCardsSubscriber, EditStringsSubscriber , EditRelicsSubscriber { // 实现接口
public static String NowPlayer=null;

    public ExampleMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addSaveField("Tieba", this);
    }

    public static void initialize() {
        new ExampleMod();
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数

    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        new AutoAdd("Tieba") // 这里填写你在ModTheSpire.json中写的modid
                .packageFilter(Soul_P.class) // 寻找所有和此类同一个包及内部包的类（本例子是所有卡牌）
                .setDefaultSeen(true) // 是否将卡牌标为可见
                .cards(); // 开始批量添加卡牌
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
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("ZhenghuoResources/localization/"+lang+"/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("tieba", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }}
    public static AbstractPlayer.PlayerClass morengcharacter;
    @Override
    public String onSave() {
        System.out.println("正在保存");
        ChangePlayer(NowPlayer);
     return NowPlayer;

    }
    @Override
    public void onLoad(String s) {

        NowPlayer=s;
        System.out.println("成功加载");
        ChangePlayer(NowPlayer);


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
            morengcharacter=player.chosenClass;
        }

    }
}
