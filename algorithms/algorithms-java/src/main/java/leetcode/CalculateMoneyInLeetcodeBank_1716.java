package leetcode;
public class CalculateMoneyInLeetcodeBank_1716 {
    /**
     * Monday 1
     * Tuesday - Sunday: +1 everyday
     *
     * next Monday: prev Monday + 1
     *
     * at the end of nth day ? 
     *
     */
    public int totalMoney(int n){
        int weeks = n/7;
        int remainingDays = n%7;
        int mondayDeposit = 0;
        int total = 0;

        for(int w=0; w<weeks; w++){
            for (int d=0; d<7; d++){
                if (d==0){
                    mondayDeposit++;
                }
                int todayDeposit = mondayDeposit + d;
                total += todayDeposit;
            }
        }
        for(int d=0; d<remainingDays; d++){
            if (d==0){
                mondayDeposit++;
            }
            int todayDeposit = mondayDeposit + d;
            total += todayDeposit;
        }
        return total;
    }

    public static void main(String[] args){
        CalculateMoneyInLeetcodeBank_1716 sol = new CalculateMoneyInLeetcodeBank_1716();
        System.out.println(sol.totalMoney(4));
        System.out.println(sol.totalMoney(10));
        System.out.println(sol.totalMoney(20));
        System.out.println(sol.totalMoney(0));
        System.out.println(sol.totalMoney(1));
    }
}
