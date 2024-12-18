package de.thu.thutorium.api.controllers;

import de.thu.thutorium.services.implementations.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentServiceImpl studentService;
}
