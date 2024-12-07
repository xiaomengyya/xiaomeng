package upei.project.People;

import upei.project.Controller.GameController;
import upei.project.Controller.IO;

public class ZhangFei extends Legend {
    public ZhangFei() {
        super("ZhangFei", "咆哮：本回合可以多次使用“杀”");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        IO.println(self.getName() + " 使用咆哮技能：本回合杀无次数限制！");
        game.allowUnlimitedKills(self); // 调用 Game 类中的机制，允许无限制使用“杀”
    }
}

