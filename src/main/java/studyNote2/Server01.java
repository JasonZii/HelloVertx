package studyNote2;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  10:55
 */
public class Server01 extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.routeWithRegex("/sp1").handler(

                ctx -> vertx.eventBus().<String> send(
                        Service01.URL01,
                        "hello vertx1",
                        result -> {
                            if(result.succeeded())
                                System.out.println(result.result().body());
                            System.out.println("testi1");
                            ctx.response().end("hello vertx1");
                        }
                )
        );

        router.routeWithRegex("/sp2").handler(
                ctx -> vertx.eventBus().<String> send(
                        Service01.URL02,
                        "hello vertx2",
                        result -> {
                            if(result.succeeded())
                                System.out.println(result.result().body());
                            System.out.println("testi2");
                            ctx.response().end("hello vertx2");
                        }
                )
        );

        router.routeWithRegex("/sp3").handler(
                ctx -> vertx.eventBus().<String> send(
                        Service03.URL03,
                        "hello vertx3",
                        result -> {
                            if(result.succeeded())
                                System.out.println(result.result().body());
                            System.out.println("testi3");
                            ctx.response().end("hello vertx3");
                        }
                )
        );

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        vertx.deployVerticle("studyNote2.Service01");
        vertx.deployVerticle(new Service03());

    }
}
