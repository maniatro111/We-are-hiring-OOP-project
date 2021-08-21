package com.company;

public class DepartmentFactory {
    public Department factory(String type){
        if(type.equals("IT"))
            return new IT(type);
        else
        if(type.equals("Finance"))
            return new Finance(type);
        else
        if(type.equals("Marketing"))
            return new Marketing(type);
        else
            return new Management(type);
    }
}
