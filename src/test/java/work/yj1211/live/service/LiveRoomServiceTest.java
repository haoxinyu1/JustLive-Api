package work.yj1211.live.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import work.yj1211.live.LiveApplication;
import work.yj1211.live.utils.platForms.Bilibili;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LiveApplication.class)
class LiveRoomServiceTest {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private Bilibili bilibili;

    @Test
    void getRoomsByUid() {
        Long start = System.currentTimeMillis();
        liveRoomService.getRoomsByUid("4ee7d7afa8844db2997bf446c16a1359");
    }

    @Test
    void getAllRooms() {
        Long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 0; i <= 7; i++) {
            bilibili.updateAllRoomByPage(i*100 + 1, countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Long time = System.currentTimeMillis() - start;
        System.out.println("耗时：" + time/1000 + "." + time%1000 + "s");
    }
}