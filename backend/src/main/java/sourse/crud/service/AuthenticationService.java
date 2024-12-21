package sourse.crud.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sourse.crud.dto.request.AuthenticationRequest;
import sourse.crud.dto.response.AuthenticationResponse;
import sourse.crud.dto.response.UserResponse;
import sourse.crud.exception.AppException;
import sourse.crud.exception.ErrorCode;
import sourse.crud.mapper.UserMapper;
import sourse.crud.repository.UserRepository;

import java.awt.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    UserMapper userMapper;
    @NonFinal
    protected static final String SIGNER_KEY = "44G4LjP6LKI6ECmzy8p5oocl+CLSyjcKca+rNxiA3bajEWAJVPvaA+16f/J7d8dz";
    public AuthenticationResponse login(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername()) 
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXITTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authentication(true)
                .build();
    }

  public UserResponse getUserDetailsFromToken(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);

      JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
      if (!signedJWT.verify(verifier)) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }

      JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
      String username = claims.getSubject();

      var user = userRepository.findByUsername(username)
          .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

      return userMapper.toUserResponse(user);

    } catch (Exception e) {
      log.error("Failed to parse or verify token", e);
      throw new RuntimeException("Failed to parse or verify token", e);
    }
  }


  String generateToken(String username) {
        try {
            // Tạo phần header cho JWS
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

            // Tạo phần payload với các thông tin claim
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer("luanthnh")
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                    ))
                    .claim("luanthnh","Spring boot")
                    .build();


            SignedJWT signedJWT = new SignedJWT(header, claimsSet);


            MACSigner signer = new MACSigner(SIGNER_KEY.getBytes());
            signedJWT.sign(signer);


            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Failed to generate token", e);
        }
    }
}
