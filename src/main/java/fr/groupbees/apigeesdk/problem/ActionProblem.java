package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ActionProblem extends AbstractThrowableProblem {

    public ActionProblem(String msg) {
        super(ErrorConstants.ACTION_TYPE,"Illegal Argument" , Status.BAD_REQUEST, msg);
    }

}