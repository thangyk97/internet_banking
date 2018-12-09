package soict.distribuitedSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.*;

import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	private UserRepositoryA a;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue" ,"/user");
		config.setApplicationDestinationPrefixes("/app");
		config.setUserDestinationPrefix("/user");
	}

	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		stompEndpointRegistry.addEndpoint("/room")
				.withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) 
					throws AuthenticationException{
				
				StompHeaderAccessor accessor =
						MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					String username = accessor.getFirstNativeHeader("username");
					String password = accessor.getFirstNativeHeader("password");
					List<User> users =  a.fetchUserBy(username, password);
										
					if (!users.isEmpty() && users.get(0).getRole() < 10 ) {
						List<GrantedAuthority> authorities = new ArrayList<>();
						authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
						Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(auth);
						accessor.setUser(auth);
					} else {
						throw new BadCredentialsException("Bad credentials for user " + username);
					}
				}
				return message;
			}
		});
	}

}
