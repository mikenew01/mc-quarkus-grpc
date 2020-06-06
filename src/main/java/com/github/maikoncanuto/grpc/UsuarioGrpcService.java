package com.github.maikoncanuto.grpc;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class UsuarioGrpcService extends UsuarioGrpc.UsuarioImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioGrpcService.class);

    @Override
    public void save(UsuarioRequest request, StreamObserver<UsuarioResponse> responseObserver) {
        LOGGER.info("[*] - Entrou na chamada GRPC - Usuario");

        UsuarioResponse response = UsuarioResponse.newBuilder()
                .setNome(request.getNome())
                .setSobreNome(request.getSobreNome())
                .setId("01")
                .setIdade(1)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();

        LOGGER.info("[*] - Chamada finalizada e retorno enviado - Usuario");

    }
}
