package studyNote1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerRequest;

import java.io.IOException;
import java.util.function.Consumer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.16  23:26
 */
public class VertxTest extends AbstractVerticle {

    public static void main(String[] args) throws IOException {
        String verticleID = VertxTest.class.getName();
        runExample(verticleID);
    }

    @Override
    public void start() throws Exception {

        final Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        // router.get("/hello")表示所监听URL路径
        router.get("/hello").handler(new Handler<RoutingContext>() {

            public void handle(RoutingContext event) {
                event.response().putHeader("content-type", "text/html").end("Hello World");
            }
        });
        // 传递方法引用，监听端口
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {

            public void handle(HttpServerRequest event) {
                router.accept(event);
            }
        }).listen(8080);// 监听端口号
    }

    public static void runExample(String verticleID) {
        VertxOptions options = new VertxOptions();

        Consumer<Vertx> runner = vertx -> {
            vertx.deployVerticle(verticleID);
        };
        // Vert.x实例是vert.x api的入口点，我们调用vert.x中的核心服务时，均要先获取vert.x实例，
        // 通过该实例来调用相应的服务，例如部署verticle、创建http server
        Vertx vertx = Vertx.vertx(options);
        runner.accept(vertx);
    }
}