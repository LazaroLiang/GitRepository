import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        String s[] = System.getProperty("java.class.path").split(";");
        for (String string : s) {
            System.out.println(string);
        }
        ApplicationContext context=new ClassPathXmlApplicationContext("file:C:\\Users\\I342400\\Documents\\My Received Files\\ProtalTest1.0\\web\\WEB-INF\\applicationContext.xml");

    }
}
