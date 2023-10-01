package experimental;

import java.util.HashMap;

public class HashMapTest{
  public static void main(String[] args) {
    HashMap<String, Integer> hashMap = new HashMap<>() {{
      put("bean", 5);
      put("turtle", 514);
    }};

    System.out.println(hashMap);
  }
}
