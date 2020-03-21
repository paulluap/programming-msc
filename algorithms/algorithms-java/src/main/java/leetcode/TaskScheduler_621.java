package leetcode;

import sun.jvm.hotspot.utilities.soql.MapScriptObject;

import javax.swing.text.AsyncBoxView;
import java.util.*;

public class TaskScheduler_621 {

    public static void main(String[] args) {
        char[] tasks = new char[]{ 'A','A','A','A','A','A','B','C','D','E','F','G'};
        TaskScheduler_621 solution = new TaskScheduler_621();
        solution.leastInterval(tasks, 2);
    }

    public int leastInterval(char[] tasks, int n) {
        List<Character> schedule = new ArrayList<>();

        Map<Character,Integer> map = new HashMap<Character, Integer>();
        //using priority q because we want to handle tasks repeated the most (to insert them among other tasks)
        //so we don't end up with all the repeated tasks that have to be splitted by idle states
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));
        for(char t : tasks){
            Integer c = map.get(t);
            if (c==null) {
                c = 0;
            }
            map.put(t, c+1);
        }
        pq.addAll(map.keySet());
        int[] history = new int[26];

        //everything not allowed is stored here
        Queue<Character> waitingQ = new LinkedList<>();

        while(!pq.isEmpty()){
            boolean allowNextTask = false;
            Character k =  null;
            //try getting the next allowed task
            while(!allowNextTask && !pq.isEmpty() ){
                k = pq.remove();
                allowNextTask = allowNext(k, history, schedule.size(), n);
                if (!allowNextTask) {
                    waitingQ.add(k);
                }
            }

            if (!allowNextTask){
                //we can do nothing but add an idle state
                schedule.add('i');
                while(!waitingQ.isEmpty()){
                    pq.add(waitingQ.remove());
                }
                continue;
            }else{
                //ok we can schedule the task now
                //add key and decrement count
                schedule.add(k);
                history[k-'A']= schedule.size();
                int r = map.get(k) - 1;
                if (r==0){
                    map.remove(k);
                }else{
                    map.put(k, r);
                    pq.add(k);
                }
                while(!waitingQ.isEmpty()){
                    pq.add(waitingQ.remove());
                }
            }
        }
        for(char c : schedule){ System.out.println(c); }
        return schedule.size();
    }

    /**
     * if we scheduled t, t should be more than interval distant from the last t
     */
    private boolean allowNext(Character t,  int[] history, int currentSize, int interval){
        return history[t-'A']==0 || currentSize - history[t-'A'] >= interval;
    }

}
