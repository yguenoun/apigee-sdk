package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class SpecProblem extends AbstractThrowableProblem {


    public SpecProblem() {
        super(ErrorConstants.DEFAULT_TYPE, "Spec Error", Status.INTERNAL_SERVER_ERROR, "Problem while reading spec file");
    }

}