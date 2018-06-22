package studyNote2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.io.IOException;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  16:15
 */
public class Server03 extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        final Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
//        router.post("/hello").handler(this::handle);
        router.get("/hello").handler(new Handler<RoutingContext>() {

            public void handle(RoutingContext event) {
                event.response().putHeader("content-type", "text/html").end("Hello World");
            }
        });

        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {

            public void handle(HttpServerRequest event) {
                router.accept(event);
            }
        }).listen(8080);

    }


    public static void run(String verticleID) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(verticleID);

    }


    public static void main(String[] args) throws IOException {
        String verticleID = studyNote2.Server03.class.getName();
        run(verticleID);
    }

}
