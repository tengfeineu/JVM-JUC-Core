package base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtf@citic.com
 * @version 1.0.0
 * @ClassName HashMapCase.java
 * @Description TODO;
 * @createTime 2022年04月13日 09:16:00
 */
public class HashMapCase {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap();

        map.put("大飞哥", "2");
        String result = map.put("大飞哥", "3");

        System.out.println(result);
        System.out.println(map.get("大飞哥"));
    }
}
