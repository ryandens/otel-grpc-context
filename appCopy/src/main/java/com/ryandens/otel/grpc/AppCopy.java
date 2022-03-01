package com.ryandens.otel.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import org.hypertrace.copy.example.Copyhelloworld.CopyRequest;
import org.hypertrace.copy.example.Copyhelloworld.CopyResponse;
import org.hypertrace.copy.example.GreeterCopyGrpc.GreeterCopyImplBase;

public class AppCopy {

  public static void main(String[] args) throws IOException, InterruptedException {
    final int port = 8081;
    Server server =
        ServerBuilder.forPort(port)
            .addService(
                new GreeterCopyImplBase() {
                  @Override
                  public void sayHelloCopy(
                      CopyRequest request, StreamObserver<CopyResponse> responseObserver) {
                    String message = "Hello " + request.getName();
                    CopyResponse build = CopyResponse.newBuilder().setMessage(message).build();
                    responseObserver.onNext(build);
                    responseObserver.onCompleted();
                  }
                })
            .build();
    server.start();
    System.out.println("server started on port " + port);
    server.awaitTermination();
  }
}
