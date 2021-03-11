import com.tianqi.ProductApplication;
import com.tianqi.mq.MqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
public class SendTest {
    private MqSender mqSender;

    @Autowired
    public void setMqSender(MqSender mqSender) {
        this.mqSender = mqSender;
    }

    @Test
    public void sendMsg() throws InterruptedException {
        HashMap<String, Object> properties = new HashMap<>();
        mqSender.send("我的消息",properties);
        Thread.sleep(100000);
    }
}
