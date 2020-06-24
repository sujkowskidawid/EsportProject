package org.esport.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)

public class GameInfo {

    private String date;
    private String name;
    private String status;
    private String stream;

    @Override
    public String toString(){
        return "Data meczu = "+date+
                name + " vs " +name+" Link do meczu: "+stream
                + "Wynik meczu: "+status;
    }
}
