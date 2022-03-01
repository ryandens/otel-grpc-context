package com.ryandens.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import org.hypertrace.example.GreeterGrpc;
import org.hypertrace.example.GreeterGrpc.GreeterStub;
import org.hypertrace.example.Helloworld.Request;
import org.hypertrace.example.Helloworld.Response;

public final class Client {

  public static void main(final String[] args) throws InterruptedException {
    System.out.println("Starting client");
    ManagedChannel managedChannel =
        ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
    GreeterStub greeterStub = GreeterGrpc.newStub(managedChannel);
    CountDownLatch countDownLatch = new CountDownLatch(1);
    StreamObserver<Response> responseObserver =
        new StreamObserver<>() {
          @Override
          public void onNext(Response value) {
            System.out.println(value.getMessage());
          }

          @Override
          public void onError(Throwable t) {
            countDownLatch.countDown();
            System.out.println(t.getMessage());
          }

          @Override
          public void onCompleted() {
            countDownLatch.countDown();
            System.out.println("Completed");
          }
        };
    greeterStub.sayHello(Request.newBuilder().setName("foo").build(), responseObserver);
    countDownLatch.await();
  }
}
