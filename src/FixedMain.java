import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BigDecimal euler = BigDecimal.ONE;
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a Quantidade de Termos na aproximação?");
        int appor = sc.nextInt();
        System.out.println("Qual a Quantidade de Threads a Utilizar?");
        int thr_num = sc.nextInt();
        ExecutorService execFixed = Executors.newFixedThreadPool(thr_num);

        for (int i = 1; i <= appor; i++) {
            Callable<BigDecimal> task = new CalcFact(i);
            BigDecimal fac = BigDecimal.ZERO;
            fac = fac.add(execFixed.submit(task).get());
            euler= euler.add(BigDecimal.ONE.divide(fac, appor, RoundingMode.HALF_UP));
        }
        System.out.println(euler);
        System.out.println(Thread.activeCount());
        execFixed.shutdown();
    }


 }