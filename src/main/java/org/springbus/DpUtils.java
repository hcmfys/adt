package org.springbus;

import com.evergrande.common.utils.JsonUtil;
import com.evergrande.common.utils.MathUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DpUtils {

    public static void bp(List<Bag> bagList, long remainAmount, int index, long actualAmount) {
        if (index >= bagList.size() || remainAmount <= 0) {
            return;
        } else {
            Bag bag = bagList.get(index);
            if (bag.getAmount() <= remainAmount) {
                actualAmount += bag.getUseAmount();
                System.out.println("背包全部装进=" + JsonUtil.toJsonString(bag));
                System.out.println("当前背包价值为" + actualAmount);
                remainAmount = remainAmount - bag.getAmount();
            } else {
                System.out.println("背包装不进去=" + JsonUtil.toJsonString(bag));
            }
            bp(bagList, remainAmount, index + 1, actualAmount);
        }
    }

    public static void main(String[] args) {
        List<Bag> bagList = new ArrayList<>();
        Bag bag = new Bag();
        bag.setId(0);
        bag.setUseAmount(50);
        bag.setAmount(100);
        bag.setWeight(MathUtils.divide(bag.getUseAmount() * 100, bag.getAmount(), 0).longValue());
        bagList.add(bag);

        for (int i = 1; i <= 5; i++) {
            Bag bag1 = new Bag();
            bag1.setId(i);
            bag1.setUseAmount(RandomUtils.nextLong(10, 100));
            bag1.setAmount(RandomUtils.nextLong(100, 500));
            bag1.setWeight(MathUtils.divide(bag1.getUseAmount() * 100, bag1.getAmount(), 0).longValue());
            bagList.add(bag1);
        }

        Bag bag2 = new Bag();
        bag2.setId(6);
        bag2.setUseAmount(60);
        bag2.setAmount(120);
        bag2.setWeight(MathUtils.divide(bag2.getUseAmount() * 100, bag2.getAmount(), 0).longValue());
        bagList.add(bag2);

        System.out.println("bagList=" + JsonUtil.toJsonString(bagList));
        bagList.sort(Comparator.comparing(Bag::getWeight).reversed().thenComparing(Bag::getAmount));
        System.out.println("bagList=" + JsonUtil.toJsonString(bagList));
        bp(bagList, 500, 0, 0);
    }
}
