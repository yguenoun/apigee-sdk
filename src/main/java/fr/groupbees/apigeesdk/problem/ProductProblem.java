package fr.groupbees.apigeesdk.problem;

import fr.groupbees.apigeesdk.utils.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ProductProblem extends AbstractThrowableProblem {

    public ProductProblem(String msg) {
        super(ErrorConstants.DEFAULT_TYPE, "Product Error", Status.INTERNAL_SERVER_ERROR, msg);
    }

}
