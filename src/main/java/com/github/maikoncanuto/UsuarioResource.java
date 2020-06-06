package com.github.maikoncanuto;

import com.github.maikoncanuto.dtos.UsuarioDTO;
import com.github.maikoncanuto.grpc.UsuarioGrpc;
import com.github.maikoncanuto.grpc.UsuarioRequest;
import com.github.maikoncanuto.grpc.UsuarioResponse;
import io.quarkus.grpc.runtime.annotations.GrpcService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    @GrpcService("usuarioHost")
    UsuarioGrpc.UsuarioBlockingStub usuarioBlockingStub;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(UsuarioDTO usuario) {
        UsuarioRequest usuarioRequest = UsuarioRequest.newBuilder()
                .setIdade(usuario.getIdade())
                .setNome(usuario.getNome())
                .setSobreNome(usuario.getSobreNome())
                .build();

        UsuarioResponse usuarioResponse = usuarioBlockingStub.save(usuarioRequest);

        UsuarioDTO usuarioRetorno = new UsuarioDTO();

        usuarioRetorno.setNome(String.format("[id: %s] - %s %s",
                usuarioResponse.getId(),
                usuarioResponse.getNome(),
                usuarioResponse.getSobreNome()));

        return Response.ok(usuarioRetorno).build();
    }


}
