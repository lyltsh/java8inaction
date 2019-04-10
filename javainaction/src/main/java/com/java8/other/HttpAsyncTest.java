package com.java8.other;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by leiyulin on 2019-04-10
 */
public class HttpAsyncTest {
    public static HttpAsyncClient client;

    public static CompletableFuture<String> getRequestData(String url) {
        CompletableFuture resultFuture = new CompletableFuture<>();
        HttpAsyncRequestProducer requestProducer = HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer responseConsumer = new BasicAsyncResponseConsumer();
        FutureCallback futureCallBack = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                resultFuture.complete(httpResponse);
            }

            @Override
            public void failed(Exception e) {
                resultFuture.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                resultFuture.cancel(true);
            }
        };

        client.execute(requestProducer, responseConsumer, futureCallBack);
        return resultFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = HttpAsyncTest.getRequestData("http://www.jd.com");
        System.out.println(result.get());
    }
}
