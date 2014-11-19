import com.msg.enums.Cfg;


public class CodeTest {

	public static void main(String[] args) {
		Cfg.valueOf("BAIDU_PUSH_API_KEY1").setValue("12345");
		System.out.print(Cfg.BAIDU_PUSH_API_KEY.getValue());
	}

}
