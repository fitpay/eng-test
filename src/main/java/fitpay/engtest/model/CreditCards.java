package fitpay.engtest.model;

import java.util.List;

/**
 * Model defining Credit Cards Json object structure
 */
public class CreditCards {

    private List<CreditCard> results;

    public List<CreditCard> getResults() {
        return results;
    }

    public void setResult(List<CreditCard> results) {
        this.results = results;
    }

}
