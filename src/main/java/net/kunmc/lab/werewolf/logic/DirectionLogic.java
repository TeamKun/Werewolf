package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import net.kunmc.lab.werewolf.player.ActorList;
import net.kunmc.lab.werewolf.player.RoleMeta;
import net.kunmc.lab.werewolf.util.MessageUtil;

import java.util.List;

public class DirectionLogic {
    /**
     * ゲーム開始演出
     * */
    static void gameStart() {
        MessageUtil.sendTitleAll("マイクラ人狼開始", "", 20, 60, 20);

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
}
