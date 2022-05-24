package by.whatson.userservice.web.controller;

import by.whatson.domain.User;
import by.whatson.userservice.service.UserApiService;
import by.whatson.userservice.util.mapper.UserMapper;
import by.whatson.userservice.web.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class InternalApiController {
    private final UserMapper userMapper;
    private final UserApiService userApiService;

    public InternalApiController(UserMapper userMapper, UserApiService userApiService) {
        this.userMapper = userMapper;
        this.userApiService = userApiService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestParam int page, @RequestParam int size, @RequestParam(required = false, defaultValue = "false") boolean mailedOnly){
        List<User> allUsers = mailedOnly ? userApiService.getAllMailedUsers(page, size) : userApiService.getAllUsers(page, size);
        List<UserResponseDto> ures = allUsers.stream().map(userMapper::mapUserToUserRespDto).collect(Collectors.toList());
        return new ResponseEntity<>(ures, HttpStatus.OK);
    }
}
