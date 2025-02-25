package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class IOProblem extends AbstractThrowableProblem {

    public IOProblem(String msg) {
        super(ErrorConstants.DEFAULT_TYPE, "IO Error", Status.INTERNAL_SERVER_ERROR, String.format("Problem while handling bundle: '%s'", msg));
    }

}