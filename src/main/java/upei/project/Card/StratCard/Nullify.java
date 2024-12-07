package upei.project.Card.StratCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

//Wu Xie Ke Ji
public class Nullify extends TacticCard {
    public Nullify(int num) {
        super("无懈可击", "用于取消对自己的锦囊效果（如“顺手牵羊”）", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + " 使用了无懈可击，取消了当前的锦囊牌效果！");
        // 锦囊牌的具体取消逻辑在调用点处理
    }
}

