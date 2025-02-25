package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class GlobalProblem extends AbstractThrowableProblem {

    public GlobalProblem (String status, String msg) {
        super(ErrorConstants.DEFAULT_TYPE, "Problem with Apigee API", Status.valueOf(status), msg);
    }

}

