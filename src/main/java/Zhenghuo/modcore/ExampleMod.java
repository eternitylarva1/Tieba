package Zhenghuo.modcore;

import Zhenghuo.card.*;
import Zhenghuo.otherplayer.OtherPlayerHelper;
import Zhenghuo.relics.CultistMask;
import Zhenghuo.relics.StrongCharacter;
import Zhenghuo.utils.ScreenDarkener;
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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import extendedui.ui.controls.EUITextBoxInput;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static Zhenghuo.actions.ChangePlayerAction.ChangePlayer;
import static com.megacrit.cardcrawl.core.Settings.language;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


@SpireInitializer
public class ExampleMod implements PostInitializeSubscriber,PostBattleSubscriber,OnPlayerTurnStartSubscriber,EditKeywordsSubscriber,PostDungeonInitializeSubscriber,OnStartBattleSubscriber, CustomSavable<String>,EditCardsSubscriber, EditStringsSubscriber , EditRelicsSubscriber { // 实现接口
public static String NowPlayer=null;
public static String Tips="";
public static boolean StartRecord=false;
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
        BaseMod.loadCustomStringsFile(PowerStrings.class, "ZhenghuoResources/localization/" + lang + "/powers.json");// 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"

    }


    public static ArrayList<ScreenDarkener> screendarkeners=new ArrayList();
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new StrongCharacter(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
       //BaseMod.addRelic(new CultistMask(), RelicType.SHARED);
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
        UITorenders.clear();
        for (ScreenDarkener screenDarkener : screendarkeners) {
            screenDarkener.dispose();
        }
        screendarkeners.clear();
    }

    @Override
    public void receivePostDungeonInitialize() {
        if(!Settings.isEndless){
            NowPlayer = null;
            morengcharacter=player.chosenClass;
        }

    }
public static ArrayList<EUITextBoxInput> UITorenders = new ArrayList<>();
    @Override
    public void receiveOnPlayerTurnStart() {
        //todo 尝试完成结算题目的逻辑
        /*
        if(GameActionManager.turn==1){
            for (AbstractMonster monster : getCurrRoom().monsters.monsters) {
                 final EUITextBoxInput descriptionInput;
                descriptionInput=(EUITextBoxInput) new EUITextBoxInput(EUIRM.images.rectangularButton.texture(),
                        new EUIHitbox(monster.drawX-30, monster.drawY+monster.hb_h*1.5f, scale(100), scale(40)).setIsPopupCompatible(true))
                        .setHeader(FontHelper.topPanelAmountFont, 0.8f, Settings.GOLD_COLOR, "请输入答案")
                        .setHeaderSpacing(1f)
                        .setColors(Color.GRAY, Settings.CREAM_COLOR)
                        .setAlignment(0.5f, 0.1f)
                        .setFont(FontHelper.cardDescFont_N, 0.8f)
                        .setBackgroundTexture(EUIRM.images.rectangularButton.texture());
                UITorenders.add(descriptionInput);
                MonsterAddFieldsPatch.f_Inputers.set(monster,descriptionInput);
                MonsterAddFieldsPatch.f_questions.set(monster,Calculate.generateMathQuestion());
            }



        }*/
    }

    @Override
    public void receivePostInitialize() {/*
        for(int i=0;i<35;i++) {
            CardCrawlGame.characterManager.getCharacter(AbstractPlayer.PlayerClass.WATCHER).getCharStat().incrementVictory();
        }*/
    }
}
