package days.c_026;

import java.util.concurrent.Executor;

/*https://blog.csdn.net/tongdanping/article/details/79604637
 *认识Executor(接口中只有void executor(Runnable command);)
 */
public class T01_MyExecutor implements Executor {

    public static void main(String args[]){
        new T01_MyExecutor().execute(()->{
            System.out.println(Thread.currentThread().getName()+"  hello executor");
        });
    }

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }


}
