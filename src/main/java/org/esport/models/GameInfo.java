package org.esport.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String league;
    private String winner;
    private String logoOpponent1;
    private String nameOpponent1;
    private String logoOpponent2;
    private String nameOpponent2;
    private String logoLeague;
    private String leagueInfo;

}
