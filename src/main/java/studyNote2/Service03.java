package studyNote2;

import io.vertx.core.AbstractVerticle;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  10:55
 */
public class Service03 extends AbstractVerticle {

    public static final String URL03="qqq";

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(URL03,
                handler->{
                    System.out.println(handler.body());
                    System.out.println("url03");
                    handler.reply("success03");
                }
        );
    }
}
