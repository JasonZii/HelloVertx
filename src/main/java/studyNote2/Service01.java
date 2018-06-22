package studyNote2;

import io.vertx.core.AbstractVerticle;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  10:55
 */
public class Service01 extends AbstractVerticle {

    public static final String URL01="www";
    public static final String URL02="eee";

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(URL01,
                handler->{
                    System.out.println(handler.body());
                    System.out.println("url01");
                    handler.reply("success01");
                }
        );

        vertx.eventBus().consumer(URL02,
                handler->{
                    System.out.println(handler.body());
                    System.out.println("url02");
                    handler.reply("success02");
                }
        );

    }
}
