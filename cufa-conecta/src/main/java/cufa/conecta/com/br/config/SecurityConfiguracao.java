package cufa.conecta.com.br.config;

import cufa.conecta.com.br.resources.user.AutenticacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {
    private final AutenticacaoRepository autenticacao;
    private final AutenticacaoEntryPoint autenticacaoEntryPoint;

    public SecurityConfiguracao(AutenticacaoRepository autenticacao, AutenticacaoEntryPoint autenticacaoEntryPoint) {
        this.autenticacao = autenticacao;
        this.autenticacaoEntryPoint = autenticacaoEntryPoint;
    }

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/usuarios/**"),
            new AntPathRequestMatcher("/error/**")
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .cors(Customizer.withDefaults())

                .csrf(CsrfConfigurer<HttpSecurity>::disable)

                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )

                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(autenticacaoEntryPoint))

                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean() { return new AutenticacaoEntryPoint(); }

    @Bean
    public AutenticacaoFilter jwtAuthenticationFilterBean() {
        return new AutenticacaoFilter(autenticacao, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() { return new GerenciadorTokenJwt(); }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.applyPermitDefaultValues();
        configuracao.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.TRACE.name()));
        configuracao.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));
        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", configuracao);

        return origem;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
