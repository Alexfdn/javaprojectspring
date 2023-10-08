package ru.alfed.MyFirstTestAppSpringBoot.hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
@RestController
public class HelloController {
    ArrayList<String> arrayList;
    HashMap<Integer, String> hashMap;
    Integer key = 0;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
            defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "values") String values){
        if (arrayList == null) {
            arrayList = new ArrayList<String>();
        }
        arrayList.add(values);
    }
    @GetMapping("/show-array")
    public ArrayList<String> showArrayList() {
        return arrayList;
    }
    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "values") String values) {
        if (hashMap == null) {
            hashMap = new HashMap<Integer, String>();
        }
        hashMap.put(key, values);
        key++;
    }
    @GetMapping("/show-map")
    public HashMap<Integer, String> showHashMap() {
        return hashMap;
    }
    @GetMapping("/show-all-lenght")
    public String showAllLength() {
        int arrayListSize = arrayList.size();
        int hashMapSize = hashMap.size();
        return "ArrayList size: " + arrayListSize + "\nHashMap size: " + hashMapSize;
    }
}
