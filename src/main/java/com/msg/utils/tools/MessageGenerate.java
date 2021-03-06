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
    	LOGIN_SUCCESSFUL("登录成功"),
    	USERNAME_HAS_NOT_EXIST("用户名不存在"),
    	USERNAME_HAS_EXIST("用户名已存在"),
    	PASSWORD_DO_NOT_MATCH("密码错误"),
    	USER_HAS_BEEN_NOT_LOGIN("用户未登录"),
    	CFG_UPDATE_SUCCESSFUL("配置更新成功"),
    	UNEXPECTED_EXCEPTION("未知异常"),
    	ID_HAS_NOT_EXIST("ID不存在"),
    	MESSAGE_HAS_NOT_EXIST("信息不存在"),
    	MESSAGE_HAS_SEND("信息已发送"),
    	MESSAGE_HAS_BEEN_UPDATED("信息已更新"),
    	MESSAGE_HAS_BEEN_DELETED("信息已删除"),
    	PRIVATE_MESSAGE_HAVE_TO_SET_SENDER("私信必须指定发送者"),
    	NOT_SUPPORT_CHANNEL("不支持的发送方式"),
    	BAIDU_PUSH_ERROR("信息未发送成功，请检查百度推送配置"),
    	USERNAME_COULD_NOT_BE_EMPTY("用户名不能为空"),
        PASSWORD_COULD_NOT_BE_EMPTY("密码不能为空"),
    	SUBMIT_CONTENT_TOO_BIG("提交内容超出限定范围"),
    	EMAIL_COULD_NOT_BE_EMPTY("联系邮箱不能为空"),
        EMAIL_COMPOSE_ERROR("邮箱格式不正确"),
        EMAIL_LENGTH_ERROR("邮箱长度超出限制"),
        PHONE_NUMBER_COULD_NOT_BE_EMPTY("手机号码不能为空"),
        PHONE_NUMBER_COMPOSE_ERROR("手机号码格式不正确"),
        PHONE_NUMBER_LENGTH_ERROR("手机号码长度超出限制"),
        MESSAGE_TITLE_COULD_NOT_BE_EMPTY("消息标题不能为空"),
        MESSAGE_CONTENT_COULD_NOT_BE_EMPTY("消息内容不能为空"),
        DATE_FORMAT_ERROR("日期格式错误"),
        INDATE_ERROR("有效时间错误"),
        AID_COULD_NOT_BE_NULL("aid不能为空"),
        USERID_COULD_NOT_BE_NULL("userId不能为空"),
        CHANNELID_COULD_NOT_BE_NULL("channelId不能为空"),
        RECID_COULD_NOT_BE_NULL("recId不能为空"),
        SEND_CHANNEL_COULD_NOT_BE_NULL("必须选择发送方式"),
        SENDID_COULD_NOT_BE_NULL("senderId不能为空"),
        SENDERNAME_COULD_NOT_BE_NULL("senderName不能为空"),
        DEVICE_HAS_BEEN_UPDATE("设备信息已更新"),
        MSGID_COULD_NOT_BE_NULL("msgId不能为空"),
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
                    generateSubClassFile(Hint.class)));
            saveFile(messageFile,generateMessageFile(Hint.class));
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
            + "    }";

}
