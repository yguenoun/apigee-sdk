package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class DeploymentProblem extends AbstractThrowableProblem {

    public DeploymentProblem(String msg) {
        super(ErrorConstants.DEFAULT_TYPE, "Deployment Error", Status.INTERNAL_SERVER_ERROR, msg);
    }

}
