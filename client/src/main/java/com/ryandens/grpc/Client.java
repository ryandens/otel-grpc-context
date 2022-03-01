package com.ryandens.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.hypertrace.example.GreeterGrpc;
import org.hypertrace.example.GreeterGrpc.GreeterBlockingStub;
import org.hypertrace.example.Helloworld.Request;
import org.hypertrace.example.Helloworld.Response;

public final class Client {

  public static void main(final String[] args) {
    System.out.println("Starting client");
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    GreeterBlockingStub greeterStub = GreeterGrpc.newBlockingStub(managedChannel);
    Response response = greeterStub.sayHello(Request.newBuilder().setName("foo").build());
    System.out.println(response.getMessage());
  }
}
