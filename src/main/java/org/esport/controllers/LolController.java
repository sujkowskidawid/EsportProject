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

    @RequestMapping(value = "/lol", method = RequestMethod.GET)
    public String connectLOL(Model model) {
        GameInfo gameInfo = new GameInfo();
        model.addAttribute("lol", gameInfo);
        String response = connectLOL();
        parseJson(response);
        return "/lol";

    }

    public String connectLOL() {
        String response = null;
        try {
            response = new HttpService().connect(Config.APP_URL2 + "?token=" + Config.APP_ID);
        } catch (IOException e) {
            e.printStackTrace();
            response = "404";
        }
        return response;
    }

    private void parseJson(String json) {

        List<GameInfo> gameInfoList = new ArrayList<>();
        JSONArray rootArray = new JSONArray(json);
        JSONObject rootObject = rootArray.getJSONObject(0);
        for (int i = 0; i < rootObject.length(); i++) {
            GameInfo gameInfo = new GameInfo();
            gameInfo.setDate(rootObject.getString("begin_at"));
            JSONArray opponentsArray = rootObject.getJSONArray("opponents");
            for (int u = 0; u < opponentsArray.length(); u++) {
                JSONObject nameObject = opponentsArray.getJSONObject(u);
                JSONObject name2Object = nameObject.getJSONObject("opponent");
                gameInfo.setImage(name2Object.get("image_key").toString());
                gameInfo.setName(name2Object.getString("name"));
            }
            JSONArray gamesArray = rootObject.getJSONArray("games");
            for(int y = 0; y < gamesArray.length(); y++){
                JSONObject winnerObject = gamesArray.getJSONObject(y);
                gameInfo.setWinner( winnerObject.getString("winner_type"));
            }
            JSONObject leagueObject = rootObject.getJSONObject("league");
            gameInfo.setLeague(leagueObject.getString("name"));
            gameInfo.setStatus(rootObject.getString("status"));
            gameInfo.setStream(rootObject.get("official_stream_url").toString());

        }
    }
}
