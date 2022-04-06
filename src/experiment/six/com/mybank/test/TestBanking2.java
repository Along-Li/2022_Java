package experiment.six.com.mybank.test;


import experiment.six.com.mybank.domain.*;


public class TestBanking2 {
    public static void main(String[] args) {
        Bank.addCustomer("zhang", "san");
        Customer c1 = Bank.getCustomer(0);
        c1.addAccount(new SavingsAccount(500,0.017));
        c1.addAccount(new CheckingAccount(2000, 3000));
        for(int i = 0; i < Bank.getNumbersOfCustomers(); i++) {
            Customer cus = Bank.getCustomer(i);
            System.out.println(cus);
            for(int j = 0;j < cus.getNumbersOfAccounts();j++) {
                Account acc = cus.getAccount(j);
                System.out.println("第"+(j+1)+"个账户");
                if(acc instanceof SavingsAccount sa){
                    System.out.printf("计算余额 %.2f\n",sa.accumulateInterest());
                }
                else if(acc instanceof CheckingAccount ck){
                    System.out.printf("当前余额为%.2f\n",ck.getBalance());
                    System.out.printf("能够透支的钱数 %.2f\n",ck.getOverdraftAmount());
                    try {
                        ck.withdraw(6000);
                    } catch (OverdraftException e) {
                        System.out.println(e.getMessage()+"赤字为"+e.getDeficit());
                        e.printStackTrace();
                    }
                }
                else {
                    //普通账户
                    try {
                        acc.withdraw(60000);
                    } catch (OverdraftException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    System.out.printf("余额为 %.2f",acc.getBalance());
                }
            }
        }
    }
}
