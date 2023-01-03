package tracker.playground;

import java.util.HashMap;

public class Playground {
    int size;
    int toyCount;
    String address;

    Playground(String address, int size, int toycount) {
        this.size = size;
        this.toyCount = toycount;
        this.address = address;
    }

    public boolean isBig() {
        return size > 10;
    }

    public boolean hasManyToys() {
        return toyCount > 5;
    }

    public boolean isNextToMe(String myAddress) {
        return this.address.equals(myAddress);
    }

    public static void main1(String[] args) {
//        int number = 18;
//        var changed = number & (16-1); // & operator // 15
//        System.out.println(changed);
//
//        var hashMap = new HashMap<Integer, String>();
//        hashMap.put(27, "Fairy Godmother");
//
//        System.out.println(hashMap.get(27));



    }
}

// works only for powers of 2 - that's why hashmap has always 2^n buckets
// cuts of the most significant

// 00010010
// 00001111
//-----------
// 00000010



