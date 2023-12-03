//package com.pathshala.service;
//
//import com.pathshala.dao.UserEntity;
//import com.pathshala.dto.UserDTO;
//import com.pathshala.enums.UserType;
//import com.pathshala.repository.UserRepository;
//import com.pathshala.security.TokenService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//public class UserServiceTests {
//
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private TokenService tokenService;
//    @Mock
//    private SessionInfoService sessionInfoService;
//    @Spy
//    private ModelMapper modelMapper = new ModelMapper();
//    @Mock
//    private UserCourseMappingService userCourseMappingService;
//
//    @BeforeEach
//    void setup(){
//        userService = new UserService(userRepository, tokenService, sessionInfoService, modelMapper, userCourseMappingService);
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//    }
//
//    @Test
//    void tearDown(){
//        userService = null;
//    }
//
//    @Test
//    public void testSaveUserData() {
//        // Mock data
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId("exampleUserId");
//        userDTO.setPassword("examplePassword");
//
//        // Mocking userRepository behavior
//        when(userRepository.findByUserId(userDTO.getUserId())).thenReturn(Optional.empty());
//        when(userRepository.save(Mockito.any())).thenReturn(new UserEntity(1L, "test", "test", "test", "test", UserType.STUDENT, "test", "test")); // Assuming save operation succeeds
//
//        // Call the method
//        Boolean result = userService.saveUserData(userDTO);
//
//        // Assertions
//        assertTrue(result); // Check if the method returns true
//
//        // Verify userRepository method calls
//        verify(userRepository, Mockito.times(1)).findByUserId(userDTO.getUserId());
//        verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
//    }
//
//    @Test
//    public void testLogout() {
//        // Test case setup
//        Long userId = 123L;
//        when(sessionInfoService.expireSessionForUserId(Mockito.any())).thenReturn(true); // Mocking the service method call
//
//        // Call the logout method
//        Boolean result = userService.logout(userId);
//
//        // Verify that the method was called with the correct parameter
//        verify(sessionInfoService).expireSessionForUserId(userId);
//
//        // Assert the result
//        assertTrue(result); //
//    }
//
//
//}
