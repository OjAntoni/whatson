package by.whatson.userservice.web.controller;

import by.whatson.userservice.mapper.TokenMapper;
import by.whatson.userservice.service.UserService;
import by.whatson.userservice.service.UserValidationService;
import by.whatson.userservice.web.dto.RegRequestMainGroup;
import by.whatson.web.ErrorResponse;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class RegistrationController {
    private final UserValidationService userValidationService;
    private final UserService userService;
    private final TokenMapper tokenMapper;

    public RegistrationController(UserValidationService userValidationService, UserService userService, TokenMapper tokenMapper) {
        this.userValidationService = userValidationService;
        this.userService = userService;
        this.tokenMapper = tokenMapper;
    }

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody @Validated(RegRequestMainGroup.class) RegistrationRequestDto regDto,
                                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    new ErrorResponse(bindingResult.getFieldErrors()
                            .stream()
                            .map(e -> e.getField()+" : "+e.getDefaultMessage())
                            .collect(Collectors.toList())),
                    HttpStatus.BAD_REQUEST);
        }

        List<String> errors = userValidationService.validateRegistrationData(regDto);
        if (!errors.isEmpty()){
            return new ResponseEntity<>(
                    new ErrorResponse(errors),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                tokenMapper.mapTokenToTokenResponseDto(userService.createAndSaveNewUser(regDto)),
                HttpStatus.OK);
    }
}
