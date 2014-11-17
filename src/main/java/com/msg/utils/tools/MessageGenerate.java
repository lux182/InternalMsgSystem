package com.msg.utils.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.util.StringUtils;

public class MessageGenerate {

    enum Submit {
                ;
        String value;

        private Submit(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
    }

    enum Hint {
        ;
        String value;

        private Hint(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    public static void main(String[] args) throws Exception {
            String classfile = System.getProperty("user.dir") + "/src/main/java/com/msg/utils/SystemMessage.java";
            String messageFile = System.getProperty("user.dir") + "/src/main/resources/message_zh.properties";
            saveFile(classfile,systemMessageClass.replace("{content}", 
                    generateSubClassFile(Submit.class)+generateSubClassFile(Hint.class)));
            saveFile(messageFile,generateMessageFile(
                    Submit.class)+"\n"+generateMessageFile(Hint.class));
            p("Ok");
    }
    private static void saveFile(String filepath,String content) throws IOException{
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.flush();
        bw.close();
    }
    private static String generateSubClassFile(Class<? extends Enum> eclass) {
        Map<String, String> map = generateFieldMap(eclass);
        String classname = eclass.getSimpleName();
        String fields="";
        for(String key : map.keySet()){
            fields+="            "+key+" = \"{"+map.get(key)+"}\",\n";
        }
        if(!StringUtils.isEmpty(fields)){
            fields=fields.substring(0,fields.length()-2)+";";
        }
        return subClass.replace("{classname}", classname).replace("{content}", fields);
    }
    public static Map<String, String> generateFieldMap(
            Class<? extends Enum> eclass) {
        String groupName = eclass.getSimpleName();
        Method toName;
        Map<String, String> map = new TreeMap<String, String>();
        try {
            toName = eclass.getMethod("name");
            Object[] objs = eclass.getEnumConstants();
            String field;
            for (Object obj : objs) {
                field = toName.invoke(obj).toString();
                map.put(field, groupName + "." + field );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    private static String generateMessageFile(Class<? extends Enum> eclass) {
        Map<String, String> map = generateFieldMsgMap(eclass);
        String content="";
        for(String key : map.keySet()){
            content+=key+"="+map.get(key)+"\n";
        }
        return content;
    }
    public static Map<String, String> generateFieldMsgMap(
            Class<? extends Enum> eclass) {
        String groupName = eclass.getSimpleName();
        Method toName,getValue;
        Map<String, String> map = new TreeMap<String, String>();
        try {
            toName = eclass.getMethod("name");
            getValue = eclass.getMethod("getValue");
            Object[] objs = eclass.getEnumConstants();
            String field;
            for (Object obj : objs) {
                field = toName.invoke(obj).toString();
                map.put(groupName + "." + field ,chinaToUnicode(getValue.invoke(obj).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String chinaToUnicode(String str){  
        String result="";  
        for (int i = 0; i < str.length(); i++){  
            int chr1 = (char) str.charAt(i);  
            if(chr1>=19968&&chr1<=171941){
                result+="\\u" + Integer.toHexString(chr1);  
            }else{  
                result+=str.charAt(i);  
            }  
        }  
        return result;  
    }  
    public static void p(String msg) {
        System.out.println(msg);
    }

    public static String systemMessageClass = 
                "package com.msg.utils;\n"
            + "\n"
            + "public class SystemMessage {\n" 
            + "{content}"
            + "\n" 
            + "}";
    public static String subClass = 
               "\n" 
            + "    public class {classname} {\n"
            + "\n"
            + "        public static final String\n" 
            + "{content}"
            + "\n"
            + "}";

}
