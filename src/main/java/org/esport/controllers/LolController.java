package org.esport.controllers;

import org.esport.Config;
import org.esport.HttpService;
import org.esport.models.GameInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LolController {

    private List<GameInfo> gameInfoList;

    @RequestMapping(value = "/lol", method = RequestMethod.GET)
    public String connectLOL(Model model) {
        String response = connectLOL();
        parseJson(response);
        model.addAttribute("gameList", gameInfoList);
        return "lol";

    }

    public String connectLOL() {
        String response = null;
        try {
            response = new HttpService().connect(Config.APP_URL2 + "?token=" + Config.APP_ID);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
            response = "404";
        }
        return response;
    }

    private void parseJson(String json) {
        gameInfoList = new ArrayList<>();
        JSONArray rootArray = new JSONArray(json);
        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject rootObject = rootArray.getJSONObject(i);
            GameInfo gameInfo = new GameInfo();
            //gameInfo.setVs(rootObject.getString("name"));

            JSONArray opponents = rootObject.getJSONArray("opponents");

            if (opponents.length() > 1 && opponents.getJSONObject(0) != null && opponents.getJSONObject(1) != null) {
                JSONObject opponent1 = opponents.getJSONObject(0).getJSONObject("opponent");
                JSONObject opponent2 = opponents.getJSONObject(1).getJSONObject("opponent");


                gameInfo.setLogoOpponent1(opponent1.get("image_url").toString());
                gameInfo.setNameOpponent1(opponent1.getString("name"));
                gameInfo.setLogoOpponent2(opponent2.get("image_url").toString());
                gameInfo.setNameOpponent2(opponent2.getString("name"));


                JSONObject leagueObject = rootObject.getJSONObject("league");
                gameInfo.setLeague(leagueObject.getString("name"));
                gameInfo.setLogoLeague(leagueObject.get("image_url").toString());
                gameInfo.setLeagueInfo(leagueObject.get("url").toString());

                gameInfo.setStatus(rootObject.getString("status"));
                gameInfo.setStream(rootObject.get("official_stream_url").toString());
                gameInfo.setDate(rootObject.get("begin_at").toString());



                //TODO zrobić obiekt oponent czy tam zwodnik czy coś i te dane tam do niego zapisać
//                System.out.println("Mecz numer: " + i);
//                System.out.println(opponent1.get("image_url").toString());
//                System.out.println(opponent1.getString("name"));
//                System.out.println("//////////////////");
//                System.out.println(opponent2.get("image_url").toString());
//                System.out.println(opponent2.getString("name"));


                gameInfoList.add(gameInfo);
            }
        }
    }
}
