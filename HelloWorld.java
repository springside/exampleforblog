
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
 
@BTrace
public class HelloWorld {
    @OnMethod(clazz="java.lang.Thread", method="start")
    public static void onThreadStart() {
        println("thread start!");
    }
}
