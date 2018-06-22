package studyNote1;

/**
 * @Author : jasonzii @Author
 * @Description :
 * @CreateDate : 18.5.16  23:02
 */
import io.vertx.core.Vertx;

public class Main01 {

    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(new Service01());
        vertx.deployVerticle(new Server01());
        vertx.deployVerticle(new Service03());
    }
}