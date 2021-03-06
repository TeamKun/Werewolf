package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.ActorList;
import net.kunmc.lab.werewolf.player.RoleMeta;
import net.kunmc.lab.werewolf.player.TeamMeta;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kunmc.lab.werewolf.util.MessageUtil;
import net.kunmc.lab.werewolf.util.SoundUtil;
import org.bukkit.Sound;

import java.util.List;

public class DirectionLogic {
    /**
     * ゲーム開始演出
     */
    static void gameStart() {
        SoundUtil.playSoundAll(Sound.AMBIENT_CAVE);
        MessageUtil.sendTitleAll(DecorationConst.YELLOW + "マイクラ" + DecorationConst.RED + "人狼" + DecorationConst.RESET + "開始!!", "", 20, 60, 20);

        ActorList actorList = GameLogic.actorList;
        int count = actorList.size();
        List<RoleConfig> configList = ConfigManager.roleConfigList();
        int citizen = count;

        for (RoleConfig roleConfig : configList) {
            citizen -= roleConfig.people();
        }

        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇配役◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
        MessageUtil.broadcast("参加者:" + count + "人");
        if (citizen > 0) {
            MessageUtil.broadcast(RoleMeta.CITIZEN.jName + ":" + citizen + "人");
        }

        for (RoleConfig roleConfig : configList) {
            if (roleConfig.people() > 0) {
                MessageUtil.broadcast(roleConfig.role().jName + ":" + roleConfig.people() + "人");
            }
        }
        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");

    }

    /**
     * ゲーム終了演出
     */
    static void gameSet(TeamMeta winner) {
        Sound sound = Sound.BLOCK_ANVIL_USE;
        String color = "";
        switch (winner) {
            case HUMAN:
                sound = Sound.UI_TOAST_CHALLENGE_COMPLETE;
                color = DecorationConst.GREEN;
                break;
            case WEREWOLF:
                sound = Sound.AMBIENT_CAVE;
                color = DecorationConst.RED;
                break;
            default:
        }

        SoundUtil.playSoundAll(sound);
        MessageUtil.sendTitleAll(color + winner.jName, "の勝利！", 20, 60, 20);
        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇配役◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
        for (Actor actor : GameLogic.actorList.getActorList()) {
            String meg = actor.actorName();
            if (actor.isDead()) {
                meg += DecorationConst.RED + "(死亡)";
            } else {
                meg += DecorationConst.GREEN + "(生存)";
            }
            meg += DecorationConst.RESET;
            MessageUtil.broadcast(meg + ": " + actor.roleName());
        }
        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
    }
}
