package org.esport.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class GameInfo {

    private String date;
    private String name;
    private String status;
    private String stream;
    private String image;
    private String league;
    private String winner;


    @Override
    public String toString(){
        return "Data meczu = "+date+
                name + " vs " +name+" Link do meczu: "+stream
                + "Wynik meczu: "+status;
    }
}
