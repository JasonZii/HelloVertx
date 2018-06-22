package studyNote2;

import io.vertx.core.Vertx;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.21  10:55
 */
public class Main01 {

    public static void main(String[] args) throws Exception {
        Vertx vertx =Vertx.vertx();
//        vertx.deployVerticle(new Server01());

        vertx.deployVerticle(new Server02());

//        vertx.deployVerticle(new Server03());

        vertx.deployVerticle("studyNote2.Service01");
        vertx.deployVerticle(new Service03());


        //参数使用字符串路径或者对象，都可以
    }

}
