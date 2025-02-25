package fr.groupbees.apigeesdk.problem;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class GeneralExceptionHandler implements ProblemHandling, SecurityAdviceTrait {
}
