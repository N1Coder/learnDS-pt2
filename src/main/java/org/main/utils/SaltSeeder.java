package org.main.utils;

import java.util.ArrayList;
import java.util.Random;

public class SaltSeeder {
    public SaltSeeder() {
    }

    public ArrayList<Integer> getSaltNumber(Random randomSeeder, int numberLength, int rangeStop) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        while (arrayList.size() < numberLength) {
            int a = randomSeeder.nextInt(rangeStop - 1)+1;

            if (!arrayList.contains(a)) {
                arrayList.add(a);
            }
        }

        return arrayList;
    }
}