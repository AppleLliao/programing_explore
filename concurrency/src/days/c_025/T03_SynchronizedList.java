package days.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {
    List<String> srts=new ArrayList<>();
    //为每个方法上面加上了Synchoronized，使他成为并发安全的
    List<String> strsSync= Collections.synchronizedList(srts);
}
