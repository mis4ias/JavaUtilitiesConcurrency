import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.*;

public class CacheMain {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService execCache = Executors.newCachedThreadPool();
        Scanner sc = new Scanner(System.in);
        BigDecimal euler = new BigDecimal(1);
        System.out.println("Qual a Quantidade de Termos na aproximação?");
        int appor = sc.nextInt();

        for (int i = 1; i <= appor; i++) {
            Callable<BigDecimal> task = new CalcFact(i);
            BigDecimal fac = BigDecimal.ZERO;
            fac = fac.add(execCache.submit(task).get());
            euler= euler.add(BigDecimal.ONE.divide(fac,appor, RoundingMode.HALF_UP));

        }

        System.out.println(euler);
        System.out.println(Thread.activeCount());
        execCache.shutdown();
    }
}
