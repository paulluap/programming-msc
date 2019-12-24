package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

//TODO: implementation is too slow, not accepted !!!
public class RemoveKDigits_444 {

    public static void main(String[] args) {
        System.out.println(new RemoveKDigits_444().removeKdigits("1432219", 3)); //1219
        System.out.println(new RemoveKDigits_444().removeKdigits("10200", 1)); //200
        System.out.println(new RemoveKDigits_444().removeKdigits("10", 2)); //0
        System.out.println(new RemoveKDigits_444().removeKdigits("112", 1)); //11
        System.out.println(new RemoveKDigits_444().removeKdigits("1432219", 3)); //1219
    }

    public String removeKdigits(String num, int k) {
        int L = num.length();
        final List<List<Integer>> single = new ArrayList<>();
        positions(L, k,new ArrayList<>(), p->{
            if (single.isEmpty()){
                single.add(p);
            }else if (less(num, p, single.get(0))){
                single.set(0, p);
            }
        });

        StringBuilder sb = new StringBuilder();
//        System.out.println(current);
        for(int i=0; i<L; i++){
            if(!single.get(0).contains(i)){
                if(sb.length()==0 && num.charAt(i)=='0') {
                    continue;
                }
                sb.append(num.charAt(i));
            };
        }
        if (sb.length()==0) {
            sb.append(0);
        }
        return sb.toString();
    }

    private boolean less(String s, List<Integer> p1, List<Integer> p2){
        int L = s.length();
        Iterator<Integer> p1Inter = p1.iterator();
        Iterator<Integer> p2Inter = p2.iterator();
        int p1V = p1Inter.next(), p2V = p2Inter.next();

        for(int i=0, j=0; i<s.length() && j<s.length(); i++, j++){
            while(p1V == i) {
                if (p1Inter.hasNext()) {
                    p1V = p1Inter.next();
                }
                i++;
            }
            while(p2V == j){
                if (p2Inter.hasNext()){
                    p2V = p2Inter.next();
                }
                j++;
            }
            if (i>L-1 || j>L-1) {
                break;
            }
//            System.out.println("" + p1 + ", " + p2);
//            System.out.println("comp: " + i + ", " + j + ": "  +  s.charAt(i) + ", " + s.charAt(j));
            if (s.charAt(i) < s.charAt(j)) {
                return true;
            }
            if(s.charAt(i) > s.charAt(j)){
                return false;
            }
        }
        return false;
    }

    private void positions(int L, int k, List<Integer> p, Consumer<List<Integer>> func){
        if (p.size()==k){
            func.accept(new ArrayList<>(p));
            return;
        }
        for(int i=0; i<L; i++){
            if (p.size()==0 || p.get(p.size()-1)<i){
                p.add(i);
                positions(L, k, p, func);
                p.remove(p.size()-1);
            }
        }
    }

}
