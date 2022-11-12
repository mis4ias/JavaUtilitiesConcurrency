import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StealingMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BigDecimal euler = new BigDecimal(1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a Quantidade de Termos na aproximação?");
        int appor = sc.nextInt();
        ExecutorService execStealing = Executors.newWorkStealingPool();
        for (int i =1 ;i<=appor;i++) {
            Callable<BigDecimal> task = new CalcFact(i);
            BigDecimal fac = BigDecimal.ZERO;
            fac = fac.add(execStealing.submit(task).get());
            euler= euler.add(BigDecimal.ONE.divide(fac,appor, RoundingMode.HALF_UP));

        }
        System.out.println("Valor numero aproximado, truncado em "+appor+" casas é: "+euler);
        System.out.println("Quantidade de Threads Utilizadas foram: "+Thread.activeCount());
        execStealing.shutdown();
    }


}
