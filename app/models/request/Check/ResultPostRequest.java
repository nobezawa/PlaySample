package models.request.Check;

import play.data.validation.Constraints;



/**
 * Created by nobesawa on 15/06/17.
 */
public class ResultPostRequest {
    @Constraints.Required
    @Constraints.Pattern("[\\w]+")
    @Constraints.MaxLength(15)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
