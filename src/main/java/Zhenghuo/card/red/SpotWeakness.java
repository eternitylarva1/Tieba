//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Zhenghuo.card.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.SpotWeaknessAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.red.SwordBoomerang;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.WeakenPotion;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SpotWeakness extends AbstractCard {
    public static final String ID = "Spot Weakness";
    private static final CardStrings cardStrings;

    public SpotWeakness() {
        super("Spot Weakness", cardStrings.NAME, "red/skill/spot_weakness", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        try {
            Desktop.getDesktop().browse(new URI("https://sts.huijiwiki.com/wiki/"+encodeToURL(m.name)));
        } catch (Exception e) {
            e.printStackTrace();
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,AbstractDungeon.player.dialogY,"我没有权限打开浏览器",true));
        }
        if (m.hasPower(WeakPower.POWER_ID)){
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber)));
        }
    }
    public static String encodeToURL(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return input; // 如果编码失败，返回原始字符串
        }
    }
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

    public AbstractCard makeCopy() {
        return new SpotWeakness();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Spot Weakness");
    }
}
