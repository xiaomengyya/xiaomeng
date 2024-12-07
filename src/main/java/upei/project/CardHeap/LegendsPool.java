package upei.project.CardHeap;

import upei.project.People.*;

import java.util.ArrayList;
import java.util.Collections;


public class LegendsPool {
    private ArrayList<Legend> legends;

    public LegendsPool() {
        legends = new ArrayList<>();
        initializeLegends();
    }

    private void initializeLegends() {
        legends.add(new GuanYu());
        legends.add(new ZhangFei());
        legends.add(new LiuBei());
        legends.add(new CaoCao());
        legends.add(new DiaoChan());
        legends.add(new SunShangxiang());
    }


    public Legend assignRandomLegend() {
        if (!legends.isEmpty()) {
            Collections.shuffle(legends);
            return legends.remove(0); // 分配并移除已分配的 Legend
        }
        return null; // 如果没有 Legend 可分配
    }

}
