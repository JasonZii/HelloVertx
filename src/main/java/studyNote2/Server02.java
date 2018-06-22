package studyNote2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import util.Message;
import util.MsgCodec;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  14:00
 */
public class Server02 extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
//        router.route().handler(BodyHandler.create());
//        vertx.eventBus().registerDefaultCodec(Message.class, new MsgCodec());

/*        router.post("/sp1").handler(this::run1);
        router.post("/sp2").handler(this::run2);*/
        router.get("/sp3").handler(this::run3);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        vertx.deployVerticle("studyNote2.Service01");
        vertx.deployVerticle(new Service03());

    }


/*    private void run1(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        vertx.eventBus().<String> send(
                Service01.URL01,
                "hello vertx1",
                result -> {
                    if(result.succeeded())
                        System.out.println(result.result().body());
                    System.out.println("testi1");

                    String msg = result.result().body();


                    writeData(response, msg);
                }

        );
    }

    private void run2(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        vertx.eventBus().<String> send(
                Service01.URL02,
                "hello vertx2",
                result -> {
                    if(result.succeeded())
                        System.out.println(result.result().body());
                    System.out.println("testi2");

                    String msg = result.result().body();


                    writeData(response, msg);
                }

        );
    }*/

    private void run3(RoutingContext routingContext) {
//        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        routingContext.response()
                .end("hello xxxxxxx");
        vertx.eventBus().<String> send(
                Service03.URL03,
                "hello vertx3",
                result -> {
                    if (result.succeeded())
                        System.out.println(result.result().body());
                    System.out.println("testi3");

                    String msg = result.result().body();


                    writeData(response, msg);

                }
        );





    }

    /**
     * 输出数据
     */
    private void writeData(HttpServerResponse response, String resultJson) {
       /* response.putHeader("content-type", "application/json");
        response.putHeader("charset", "UTF-8");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.putHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.putHeader("Access-Control-Expose-Headers","deviceId,osType,bi_auth,versionCode,regionCode,manufacturer");
        response.putHeader("Access-Control-Allow-Headers","Content-Type,deviceId,osType,bi_auth,versionCode,regionCode,manufacturer");
       */ response.end(resultJson);
        response.close();
    }


}
