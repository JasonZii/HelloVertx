package studyNote2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.logging.Logger;

/**
 * @Author : jasonzii @Author
 * @Description : executeBlocking   阻塞方法
 * @CreateDate : 18.7.17  22:24
 */
public class TestBlock extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/test").handler(this::handlert);

        vertx.createHttpServer().requestHandler(router::accept).listen(8087);

    }


    public void handlert(RoutingContext routingContext) {

        HttpServerResponse response = routingContext.response();

        vertx.executeBlocking(future -> {           //阻塞方法

            String t = "blocking";

            future.complete(t);

        }, res -> {                                 //回调

            if (res.succeeded()) {
                System.out.println("succeed");
                writeData(response, res.result().toString());
            } else {
                System.out.println("warning");
            }

        });
    }

    public static void main(String[] args) {

        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new TestBlock());

    }

    /**
     * 输出数据
     */
    private void writeData(HttpServerResponse response, String resultJson) {
        response.putHeader("content-type", "application/json");
        response.putHeader("charset", "UTF-8");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.putHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.putHeader("Access-Control-Expose-Headers",
                "deviceId,osType,bi_auth,versionCode,regionCode,manufacturer");
        response.putHeader("Access-Control-Allow-Headers",
                "Content-Type,deviceId,osType,bi_auth,versionCode,regionCode,manufacturer");

        response.end(resultJson);
    }

}
