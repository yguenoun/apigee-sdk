package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class AuthProblem extends AbstractThrowableProblem {

    public AuthProblem(String msg) {
        super(ErrorConstants.DEFAULT_TYPE, "Invalid authentication credentials", Status.UNAUTHORIZED, msg);
    }

}