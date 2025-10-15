//GoogleAuthService.java
package com.matias.orders_api.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleAuthService {

    // 👇 Client ID de tu proyecto en Google Cloud (el mismo que en application.properties)
    private static final String CLIENT_ID =
            "968552378857-5mvuq40sh79kjsa0jakl5tsfllhnub6.apps.googleusercontent.com";

    public GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        // Construimos el verificador con el clientId correcto
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        )
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken != null) {
            System.out.println("✅ Token de Google válido para: " + idToken.getPayload().getEmail());
            return idToken.getPayload();
        } else {
            System.err.println("❌ Token de Google inválido o expirado");
            return null;
        }
    }
}