import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class CalcFact implements Callable<BigDecimal> {
    private int count;

    public CalcFact(int count){
        this.count=count;
    }
    public  BigDecimal fatorial(int n){
        if(n<=1){
            return BigDecimal.ONE;
        }else{
            return(BigDecimal.valueOf(n).multiply(fatorial(n-1)));
        }
    }
    @Override
    public BigDecimal call(){
        return fatorial(this.count);
    }

}
