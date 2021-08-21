package com.company;

import java.util.Objects;

public class Constraint {
    Double inf_limit;
    Double sup_limit;
    Constraint(Double inf_limit,Double sup_limit){
        if(inf_limit==null)
            this.inf_limit=-9999999.0;
        else
            this.inf_limit=inf_limit;
        if(sup_limit==null)
            this.sup_limit=99999999.0;
        else
            this.sup_limit=sup_limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraint that = (Constraint) o;
        return Objects.equals(inf_limit, that.inf_limit) &&
                Objects.equals(sup_limit, that.sup_limit);
    }

}
