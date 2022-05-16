package by.whatson.userservice.web.controller;

import by.whatson.domain.User;
import by.whatson.userservice.util.mapper.UserMapper;
import by.whatson.userservice.service.UserService;
import by.whatson.userservice.util.validation.RegRequestSettingsGroup;
import by.whatson.userservice.web.dto.UserSettingsRequestDto;
import by.whatson.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class ProfileManagementController {
    private final UserMapper userMapper;
    private final UserService userService;

    public ProfileManagementController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/settings")
    public ResponseEntity<?> changeUserSettings(@RequestBody @Validated(RegRequestSettingsGroup.class) UserSettingsRequestDto dto,
                                                BindingResult bindingResult,
                                                @RequestParam UUID token){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    new ErrorResponse(bindingResult.getFieldErrors()
                            .stream()
                            .map(e -> e.getField()+" : "+e.getDefaultMessage())
                            .collect(Collectors.toList())),
                    HttpStatus.BAD_REQUEST);
        }
        if (!userService.userExists(token)){
            return new ResponseEntity<>(new ErrorResponse(List.of("Token is invalid")), HttpStatus.BAD_REQUEST);
        }
        User userWithNewSettings = userMapper.mapUserSettingsReqDtoToUser(dto);
        if (userService.updateUser(token, userWithNewSettings)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
