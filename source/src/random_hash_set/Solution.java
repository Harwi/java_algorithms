package random_hash_set;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    static class RandomizedSet {

        final int initBuckets = 100;
        final int bucketGrow = 2;
        final ArrayList<Integer>[] multiMap;
        final Random rand;
        final AtomicInteger bucketIx = new AtomicInteger(0);
        final ArrayList<Integer> buckets;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            this.multiMap = new ArrayList[initBuckets];
            this.rand = new Random();
            this.buckets = new ArrayList<>();
        }

        private synchronized void addBucket(int bucket) {
            if (bucketIx.get() + 1 >= multiMap.length) {
                bucketIx.set(0);
                buckets.set(bucketIx.get(), bucket);
            } else {
                buckets.add(bucket);
            }
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            int bucket = calcBucket(val);
            if (multiMap[bucket] == null || multiMap[bucket].isEmpty()) {
                if (multiMap[bucket] == null) {
                    multiMap[bucket] = new ArrayList<>();
                    addBucket(bucket);
                }
                multiMap[bucket].add(val);
                return true;
            } else {
                if (multiMap[bucket].contains(val)) {
                    return false;
                } else {
                    multiMap[bucket].add(val);
                    return true;
                }
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            int bucket = calcBucket(val);
            if (multiMap[bucket] != null && !multiMap[bucket].isEmpty()) {
                return multiMap[bucket].remove(Integer.valueOf(val));
            }

            return false;
        }

        private int getRandomFromBucket(int bucket) {
            return multiMap[bucket].get(rand.nextInt(multiMap[bucket].size()));
        }

        /** Get a random element from the set. */
        public int getRandom() {
            if (buckets.size() == 0) {
                return -1;
            } else {
                int randIx = rand.nextInt(buckets.size());
                if (multiMap[buckets.get(randIx)] != null && !multiMap[buckets.get(randIx)].isEmpty()) {
                    return getRandomFromBucket(buckets.get(randIx));
                } else {
                    int i = randIx - 1;
                    int j = randIx + 1;
                    while (i >= 0 || j < buckets.size()) {
                        if (i >= 0 && multiMap[buckets.get(i)] != null && !multiMap[buckets.get(i)].isEmpty()) {
                            return getRandomFromBucket(buckets.get(i));
                        } else if (j < buckets.size() && multiMap[buckets.get(j)] != null && !multiMap[buckets.get(j)].isEmpty()) {
                            return getRandomFromBucket(buckets.get(j));
                        }
                        i--;
                        j++;
                    }
                }
            }

            return -1;
        }

        private int calcHash(int val) {
            int h = val;
            h ^= (h >>> 20) ^ (h >>> 13);
            return h ^ (h >>> 7) ^ (h >>> 4);
        }

        private int calcBucket(int val) {
            return calcHash(val) & (multiMap.length - 1);
        }

//        private synchronized void increaseBuckets() {
//            Integer[] newSet = new Integer[this.set.length * bucketGrow];
//            System.arraycopy(set, 0, newSet, 0, this.set.length);
//            this.set = newSet;
//        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(-1);
        randomizedSet.remove(-2);
        randomizedSet.insert(-2);

        System.out.println("Random = " + randomizedSet.getRandom());

        randomizedSet.remove(-1);
        randomizedSet.insert(-2);

        System.out.println("Random = " + randomizedSet.getRandom());


//        randomizedSet.insert(2);
//        randomizedSet.insert(3);
//        randomizedSet.insert(4);
//        randomizedSet.insert(888);
//        randomizedSet.insert(1234567);
//
//        randomizedSet.remove(-100);
//        randomizedSet.remove(3);
//
//        System.out.println("Random = " + randomizedSet.getRandom());
//        System.out.println("Random = " + randomizedSet.getRandom());
//        System.out.println("Random = " + randomizedSet.getRandom());
    }

}
