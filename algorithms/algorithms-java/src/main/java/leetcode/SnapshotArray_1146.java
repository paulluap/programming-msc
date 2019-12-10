package leetcode;

import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 */
public class SnapshotArray_1146 {
    /**
     * s0 : 0 0 0 0 0 0 0
     * s1 : - - - - - - -
     * s2 : - - - - - - -
     * s3 : - - 1 - - - -
     * s4 : - - - 9 - - -
     *
     */
    private int snap;
    private TreeMap<Integer, Integer> /*snap*/ a[];

    public SnapshotArray_1146(int length) {
        snap = 0;
        a = new TreeMap[length];
        for(int i=0; i<length; i++){
            a[i] = new TreeMap<>();
            a[i].put(0,0);
        }
    }
    
    public void set(int index, int val) {
        a[index].put(snap, val);
    }
    
    public int snap() {
        return snap++;
    }
    
    public int get(int index, int snap_id) {
        return a[index].floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
        SnapshotArray_1146 arr = new SnapshotArray_1146(3);
        arr.set(0, 5);
        System.out.println("snap is 0 : " + (arr.snap()==0));
        arr.set(0, 6);
        System.out.println("snap 0 index 0 is 5: " + (arr.get(0, 0) == 5));
        System.out.println("snap 1 index 0 is 6: " + (arr.get(0, 1) == 6));
    }
}
